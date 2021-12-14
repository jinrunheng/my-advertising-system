## Spring Cloud 微服务架构设计实现广告系统

### 笔记
- [第二章 广告系统概览与准备工作](https://www.yuque.com/dobbykim/dtgo95/ay3atv)
- [第三章 Eureka 与 Zuul 介绍](https://www.yuque.com/dobbykim/dtgo95/if11z5)
- [第四章 微服务通用模块开发](https://www.yuque.com/dobbykim/dtgo95/gdg3kn)
- [第五章 广告投放系统的开发](https://www.yuque.com/dobbykim/dtgo95/gqs0yo)
- [第六章 广告检索系统-微服务调用](https://www.yuque.com/dobbykim/dtgo95/ziua6a)
- [第七章 广告检索系统-广告数据库索引的设计与实现](https://www.yuque.com/dobbykim/dtgo95/bzo8cd)
使用 Docker 开启 MySQL 数据库:
```bash
docker run --name ad -e MYSQL_ROOT_PASSWORD=123 -e MYSQL_DATABASE=ad -p 3306:3306 -v /Users/macbook/Desktop/myProject/my-advertising-system/db:/data -d mysql
```
进入到容器中：
```bash
docker exec -it 638d33471ae0d3374045ba08bac8f51704a16a9535ce6d97cc3b8ee1d78dd916 /bin/bash
```
进入到 MySQL 环境中：
```bash
mysql -uroot -p123
```
执行命令，完成数据表的初始化：
```bash
source /data/schema.sql
```


#### 如何开启 Eureka Server 集群：

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

 