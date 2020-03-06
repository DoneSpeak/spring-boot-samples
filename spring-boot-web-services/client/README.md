Web Service Client
===

获取wsdl文件并通过maven整合
---

### 获取wsdl文件



### 使用公共的web service服务
访问 [WebXml](http://www.webxml.com.cn/zh_cn/index.aspx) 可以获取公共的web service服务，比如天气，翻译，查询号码所在地等。  

但在进行整合中发现一些问题，jaxb2无法识别xml文件中的`ref`，经过上网查询，找到如下解决方式：

将
```xml
<s:element ref="s:schema" />
<s:any />
```

全部替换为如下代码，注意是用下面的**一行替换上面的两行**。
```xml
<s:any minOccurs="2" maxOccurs="2" />
```

### 通过maven整合
