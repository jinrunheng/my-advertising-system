## Spring Cloud 微服务架构设计实现广告系统

### 笔记
- [第二章 广告系统概览与准备工作](https://www.yuque.com/dobbykim/dtgo95/ay3atv)
- [第三章 Eureka 与 Zuul 介绍](https://www.yuque.com/dobbykim/dtgo95/if11z5)
- [第四章 微服务通用模块开发](https://www.yuque.com/dobbykim/dtgo95/gdg3kn)

如何开启 Eureka Server 集群：

在 /etc/hosts 文件中指定：
```text
127.0.0.1 server1
127.0.0.1 server2
127.0.0.1 server3
```
然后进入到父工程目录下，执行命令：
```text
mvn clean package -Dmaven.test.skip=true
```
最后到 `ad-eureka-server` 子工程的 `target` 目录下 执行如下命令:
```text
java -jar ad-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=server1
java -jar ad-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=server2
java -jar ad-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=server3
```

 