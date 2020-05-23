


## 参考


```sh
Springboot分布式锁实践（redis）
https://www.cnblogs.com/carrychan/p/9431137.html

Springboot2本地锁实践
https://www.cnblogs.com/carrychan/p/9429159.html


SpringBoot之使用Redis实现分布式锁（秒杀系统）
https://blog.csdn.net/qq_37892957/article/details/89322334


Redis分布式锁 命令
https://blog.csdn.net/weixin_38294999/article/details/89303775

Redis分布式锁解决接口幂等的两种方案
https://blog.csdn.net/qq_24598601/article/details/105876432

```



## 分布式锁使用redis命令

```sh
setnx当且仅当 key 不存在。若给定的 key 已经存在，则 setnx不做任何动作。
setnx 是『set if not exists』(如果不存在，则 set)的简写,setnx 具有原子性。 
getset先 get 旧值，后set 新值，并返回 key 的旧值(old value），具有原子性。
当 key 存在但不是字符串类型时，返回一个错误;当key 不存在的时候，返回nil ，在Java里就是 null。 
expire 设置 key 的有效期 
del 删除 key
与时间戳的结合 
分布式锁的值是按 系统当前时间 System.currentTimeMillis()+Key 有效期组成。
```



 
## RedisTemplate常用api
```sh
springboot RedisTemplate常用api
https://blog.csdn.net/b422761838/article/details/103728926

springboot之使用redistemplate优雅地操作redis
https://www.cnblogs.com/superfj/p/9232482.html

Spring Boot Redis RedisTemplate 相关API介绍
https://blog.csdn.net/qq_22339269/article/details/90812253

【深入浅出SpringBoot】RedisTemplate使用方法归纳
https://www.cnblogs.com/snake107/p/12143204.html

Springboot项目redisTemplate实现轻量级消息队列
https://www.jianshu.com/p/0c684076367e

SpringBoot整合redis——redisTemplate的使用
https://www.jianshu.com/p/c168e2b825cb
```