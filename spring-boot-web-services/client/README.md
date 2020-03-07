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

参考：  
- [关于cxf 连.net 的webservice生成客户端异常( undefined element declaration 's:schema')](https://www.cnblogs.com/hzhuxin/archive/2011/05/25/2057142.html)

### 通过maven整合

#### 使用`org.jvnet.jaxb2.maven2:maven-jaxb2-plugin`

还没有找到该插件解析 `<wsdl:xxxx>` 类标签的配置方法，因而会缺少部分service类，需要通过spring-ws创建client进行访问WS服务。

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
<plugin>
    <groupId>org.jvnet.jaxb2.maven2</groupId>
    <artifactId>maven-jaxb2-plugin</artifactId>
    <version>0.14.0</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <schemaLanguage>WSDL</schemaLanguage>
                <generatePackage>${wsdl.package}.country</generatePackage>
                <cleanPackageDirectories>true</cleanPackageDirectories>
                <schemas>
                    <schema>
                        <fileset>
                            <!-- Defaults to schemaDirectory. -->
                            <directory>${wsdl.directory}</directory>
                            <!-- Defaults to schemaIncludes. -->
                            <includes>
                                <include>countries.wsdl</include>
                            </includes>
                            <!-- Defaults to schemaIncludes -->
                            <!--<excludes>-->
                            <!--<exclude>*.xs</exclude>-->
                            <!--</excludes>-->
                        </fileset>
                    </schema>
                </schemas>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### 使用`org.codehaus.mojo:jaxws-maven-plugin`

会解析生成`<wsdl:xxx>`，从而生成连接需要的类，无需自己另外配置。

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>jaxws-maven-plugin</artifactId>
    <version>2.6</version>
    <executions>
        <execution>
            <goals>
                <goal>wsimport</goal>
            </goals>
            <configuration>
                <!-- 输出路径为：target/generated-sources/wsimport -->
                <packageName>${wsdl.package}.weather</packageName>
                <wsdlDirectory>${wsdl.directory}</wsdlDirectory>
                <wsdlFiles>
                    <wsdlFile>WeatherWS.wsdl</wsdlFile>
                </wsdlFiles>
                <keep>true</keep>
                <extension>true</extension>
            </configuration>
        </execution>
    </executions>
</plugin>
```