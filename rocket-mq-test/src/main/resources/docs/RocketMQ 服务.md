# RocketMQ 服务



## 下载地址

```sh
http://rocketmq.apache.org/release_notes/release-notes-4.6.0/
```



## 环境配置

```sh
变量名：ROCKETMQ_HOME
变量值：ROCKETMQ_HOME=D:\developer\rocketmq-all-4.6.0
```



## 启动RocketMQ服务

### 启动NAMESERVER

```sh
在命令行窗口cmd进入到RocketMQ安装目录下的bin目录；执行
start mqnamesrv.cmd
启动NAMESERVER。成功后会弹出提示框，此框勿关闭。

说明：RocketMQ NAMESERVER默认分配的jvm参数需要占用较大内存，一般pc没有这么大内存需要修改小些才行。
```



### 启动BROKER

```sh
在命令行窗口cmd进入到RocketMQ安装目录下的bin目录，然后执行
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
启动BROKER。成功后会弹出提示框，此框勿关闭。
说明：RocketMQ BROKER默认分配的jvm参数需要占用较大内存，一般pc没有这么大内存需要修改小些才行。
```





##  RocketMQ插件安装

1. 克隆代码

```sh
https://github.com/apache/rocketmq-externals
git clone https://github.com/apache/rocketmq-externals.git
说明：该步骤必须先安装git
```

2. 配置设置

   克隆完成之后，进入rocketmq-externals\rocketmq-console\src\main\resources文件夹，打开application.properties进行配置。

```sh
server.contextPath=
server.port=8080
rocketmq.config.namesrvAddr=127.0.0.1:9876
spring.application.name=rocketmq-console
```



3. 编译插件

```sh
进入\rocketmq-externals\rocketmq-console文件夹，执行
mvn clean package -Dmaven.test.skip=true
进行编译,说明：该步骤必须先安装maven
```

4. 启动控制台插件

```sh
编译成功之后
cmd进入target文件夹，执行
java -jar rocketmq-console-ng-1.0.0.jar
启动rocketmq-console-ng-1.0.0.jar
```

5. 验证是否安装成功

```sh
浏览器中输入http://127.0.0.1:8080
```





## 参考

```sh
SpringBoot中优雅的使用RocketMq
https://www.cnblogs.com/SimpleWu/p/12112351.html
RocketMQ(5)---RocketMQ重试机制
https://www.cnblogs.com/qdhxhz/p/11117379.html
消息中间件（一）MQ详解及四大MQ比较
https://blog.csdn.net/wqc19920906/article/details/82193316/
RocketMQ SpringBoot 示例
https://www.jianshu.com/p/38a3596beea6
```

