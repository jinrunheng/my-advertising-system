## Spring Cloud 微服务架构设计实现广告系统

开启 Eureka Server 集群：

首先要在 /etc/hosts 文件中指定：
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

 