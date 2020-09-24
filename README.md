# spring-boot-admin

## 使用手册
### 客户端 - springboot项目
1. 修改项目pom.xml
- 添加maven依赖
```xml
    <dependency>
        <groupId>com.github.frog-warm</groupId>
        <artifactId>admin-client-spring-boot-starter</artifactId>
        <version>1.0.0.RELEASE</version>
    </dependency>
```
- 添加git-commit插件，引入git版本信息（可选）
```xml
<plugin>
    <groupId>pl.project13.maven</groupId>
    <artifactId>git-commit-id-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>revision</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <verbose>true</verbose>
        <dateFormat>yyyy-MM-dd HH:mm:ss</dateFormat>
        <generateGitPropertiesFile>true</generateGitPropertiesFile>
        <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties</generateGitPropertiesFilename>
    </configuration>
</plugin>
```

2. 添加客户端配置
```properties
spring.application.name=项目名称
spring.boot.admin.client.instance.metadata.secret=${random.uuid}
# server端启用钉钉通知时 @用户手机号
spring.boot.admin.client.instance.metadata.atMobiles=189****2020
spring.boot.admin.client.instance.prefer-ip=true
#server端地址
spring.boot.admin.client.url=http://localhost:40000
#server端账户
spring.boot.admin.client.username=admin
#server端秘钥
spring.boot.admin.client.password=admin
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=ALWAYS
management.info.git.mode=full

```
or
```yaml
spring:
  application:
    name: 项目名称
  boot:
    admin:
      client:
        instance:
          metadata:
            secret: ${random.uuid}
            atMobiles: 189****2020 #server端启用钉钉通知时 @用户手机号
          prefer-ip: true
        url: http://localhost:40000 #server端地址
        username: admin #server端账户
        password: admin #server端秘钥
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: ALWAYS
  info:
    git:
      mode: full

```
## 版本发布记录
### 1.0.0.RELEASE
- 客户端添加spring-boot-starter-actuator的权限验证
- 管理端添加钉钉通知
