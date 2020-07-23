API Guideline
===

## 定义原则

1. 对于错误信息，客户端能够通过接口返回的错误信息设计一个自动处理流程。
1. 对于错误码，尽量重用已有的错误码，定义过多的错误码，容易给客户端开发人员造成不必要麻烦。
1. 对于代码的开发，接口必须清晰已读，不存在任何模糊定义。

## 路径

### 排序和翻页

```shell
curl users?page=1&size=100&sort=-createTime,name,+status
```

翻页一般传入两个参数作为分页条件，`page`为当前页码（可以从1开始），`size`表示每页最大数量（需要限制最大值）。  

`sort`表示排序条件，值为一个排序表达式，可同时对多个字段进行排序，字段之间以`,`分隔。每个字段前面的`-/+`表示降序和升序，默认为升序。

## Http Status和错误码

### HTTP 状态码
如下为系统中将会使用的状态码以表示意义。部分状态码存在多种意义，因而增加了一个`type`作区分。

status | type | 含义
--- | --- | ---
200 | OK | 成功操作。
400 | BAD_REQUEST | 客户端严重错误，比如提交了不合法的json内容
400 | FAILED_PRECONDITION | 业务处理错误
401 | UNAUTHENTICATED | 当没有提供或提供的认证无效
403 | FORBIDDEN | 当认证成功但是认证用户无权访问该资源时，或者由于其他安全原因而被禁止时
404 | NOT_FOUND | 当一个不存在的资源被请求时，比如请求一个不存在的用户的信息
415 | UNSUPPORTED_MEDIA_TYPE | 请求中包含了不正确的内容类型，比如提交contentType=application/xml到仅支持json的接口
422 | INVALID_ARGUMENT | 数据校验不通过
429 | RESOURCE_EXHAUSTED | 客户请求过于频繁，需要稍后再尝试
500 | INTERNAL | 服务内部错误。需要技术人员干预才能解决。
503 | UNAVAILABLE | 服务器端临时不可用，比如频繁请求第三方资源而要求稍后才能使用。可稍后重试。

**注意:** 部分接口我们会使用 `404 Not Found` 来代替`403 Forbiden` 来表示用户访问了无权访问的接口的错误，以保护用户私有数数据安全。    

### 响应结构体

## 200 OK

```js
HTTP/1.1 200
{
    ...
}
```

当请求成功时，直接将请求的结果返回到客户端，不做另外的封装。

```java
@GetMapping("")
public List<UserVo> listUsers(UserSearchQuery query) {
    ...
}

@DeleteMapping("/user/{userId:\\d+}")
public void delete(@PathValiable long userId) {
    ...
}
```

### 发生异常

```js
HTTP/1.1 XXX
{
    // 只有在业务类异常才会有code
    code: string,
    type: string,
    message: string,
    // 只要在数据校验异常才会有validErrors
    validErrors: [
       {
           type: string,
           field: string,
           message: string
       }
    ]
}
```

如下为几个例子：

1. 传输数据格式不正确
```js
HTTP/1.1 400
{
   code: "",
   type: "BAD_REQUEST",
   message: "Problems parsing JSON",
   validErrors: []
}
```
2. 用户名已被存在
```js
HTTP/1.1 400
{
   code: "1110000",
   type: "FAILED_PRECONDITION",
   message: "The username is taken.",
   validErrors: []
}
```

当客户端接收到404请求时，会执行跳转操作。因而如果一个资源不存在，均应该返回404，以让客户端执行跳转请求。

### 数据校验错误



#### 校验错误类型

类型 | 意义 | 描述
--- | --- | ---
unrecognized_type | 无法识别类型 | 一般为输入的数据结构错误
missing_field | 缺少必要字段 | 可能是漏填必填字段，或者缺少请求必须携带的字段
invalid | 校验失败，详细见文档 | 特殊校验失败，客户端可以直接展示返回信息
type_mismatch | 类型不匹配 | 一般为用户输入的数据格式错误导致类型匹配错误

#### 字段路径
**单个字段（Get请求）**
```js
// name
name
// age
age
```

**普通对象（POST,PUT,PATCH请求）**
```js
// User(String name,Book[] books), Book(String name)
{
    // name
    name: "jojo",
    // books
    books: [
        {
            // books[0].name
            name: "jump"
        }
    ]
}
```

**数组/列表（PUT,POST,PATCH）**
```js
// List<User>, User(String name)
[
    {
        // [0].name
        name: "jojo"
    }
]

// List<String>
[
    // [0]
    "jojo",
    // [1]
    "sali"
]

// User[], User(String name)
[
    {
        // [0].name
        name: "jojo"
    }
]

// String[]
[
    // 无法做单项校验
    "",
    "haha"
]
```

**Set（POST,PUT,PATCH请求）**
```js
[
    // []
    "",
    // [haha]
    "haha"
]
```

Set直接做方法参数，导致`IllegalStateException`异常，返回500结果。
```java
@GetMapping("set")
public Set<Integer> set(@Size(max = 10) Set<Integer> set);
```

**Map(`禁止`使用)**
```js
// Map<String, String>
{
    "key": "value"
}
// Map<String, User>, User(String name, int age)
{
    // !!! 暂时无法保障，可能出现 [user]
    // [user]<K>
    "user": {
        // [user].name
        "name": "jojo",
        // [user].age
        "age": 17
    }
}
```

如果Controller的方法类似如下，且校验map不通过时， 会抛出`MethodArgumentNotValidException`异常。对于该校验不通过的结果，暂时没有很好方法获取类似上面Map路径。只能等官方优化这部分路径返回了。
```java
@PostMapping("")
public createUser(@RequestBody @Valid User user);

public class User {
    ...
    private Map<@Length(min = 2) String, @NotBlank String> map;
}
```

## Controller中定义接口注意事项和技巧

### 注意事项
1. `GetMapping`方法使用基本类型请求参数(这里不包含@PathVariable参数)时，**必须**加上`@RequestParam`

没有`@RequestParam`注解的参数，spring会认为是可选参数，并尝试传入`null`值，会导致`IllegalStateException`异常，从而返回500相应。

```java
java.lang.IllegalStateException: Optional int parameter 'status' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.handleNullValue(AbstractNamedValueMethodArgumentResolver.java:245) ~[spring-web-5.1.8.RELEASE.jar:5.1.8.RELEASE]
    ...
```

```java
// 不要这样写
@GetMapping("")
public List<IdeaVo> list(@Validated @NotBlank String name, int status) {
    ...
}

// 可以这样写 
@GetMapping("")
public List<IdeaVo> list(@Validated @NotBlank String name, @RequestParam int status) {
    ...
}

// 可以这样写
public List<IdeaVo> list(@Validated @NotBlank String name, @RequestParam Integer status) {
    ...
}

// 推荐这样写
public List<IdeaVo> list(@Validated IdeaSearchQuery) {
    ...
}

@Data
public class IdeaSearchQuery {
    @NotBlank
    String name;
    @NotNull
    Integer status;
} 
```

1. **禁止** 使用 `Map` 接收客户端请求数据，**禁止** 返回`Map`或者直接`Object`类型结果。

这些定义会让接口变得不清晰，后续的开发者无法直接从代码知道这个接口应该如何定义。  

如果要定义一个K-V的接口，请定义一个K-V的类。如下：
```java
@Data
public class KeyValue {
    private String key;
    private String value;
}
```

### 使用技巧

1. 使用集合接收statuses=1,2,3请求参数

如下写法**有效**：
```java
GET users?statuses=1,2,3
@GetMapping("")
public List<User> getUsers(List<Integer> statuses) {
    ...
}

// 推荐这样写
@GetMapping("")
public List<User> getUsers(UserSearchQuery query) {
    ...
}

@Data
public class UserSearchQuery {
    private List<Integer> statuses;
}
```

如下写法**无效**：
```java
@GetMapping("")
public List<User> getUsers(List<Integer> statuses) {
    ...
}
```

## 参考

- [Google Cloud Apis#Errors @cloud.google.com](https://cloud.google.com/apis/design/errors)
- []()

