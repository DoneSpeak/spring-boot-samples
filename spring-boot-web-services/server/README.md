Web Service Server
====

启动
---

Gradle 启动：
```shell script
# 直接启动
./gradlew bootRun

# 打包后启动
./gradlew build
java -jar build/libs/spring-boot-web-service-server.1.0-SNAPSHOT.jar
```

Maven 启动：
```shell script
./mvnw spring-boot:spring
./mvnw clean package
java -jar target/spring-boot-web-service-server.1.0-SNAPSHOT.jar
```

接口测试
---

创建请求xml文件`request.xml`
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wss="https://donespeak.github.io/spring-boot-web-services">
    <soapenv:Header/>
    <soapenv:Body>
        <wss:getCountryRequest>
            <wss:name>Spain</wss:name>
        </wss:getCountryRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

发送请求
```shell script
# WSDL 1.1 规定的content-type为text/xml，而1.2为application/soap+xml;charset=UTF-8
curl --header "content-type:text/xml" -d @docs/request.xml http://localhost:8080/ws
# 请求结果的xml为压缩之后的文本，可以用xmllib2进行格式化
curl -fsSL --header "content-type:text/xml" -d @docs/request.xml http://localhost:8080/ws > output.xml && xmllint --format output.xml
```

当然你也可以用[SoapUI](https://www.soapui.org/downloads/soapui.html)这样的工具进行测试。除了现在安装包进行安装，也可以使用IDEA的SoapUI插件。

客户端使用wsdl
---

获取wsdl文件

```shell script
curl http://localhost:8080/ws/countries.wsdl --output countries.wsdl
```
