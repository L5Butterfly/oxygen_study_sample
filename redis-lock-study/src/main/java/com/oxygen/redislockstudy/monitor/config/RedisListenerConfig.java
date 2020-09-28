package com.oxygen.redislockstudy.monitor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

 /**
  * 类信息注释
  * @author Administrator
  * @date 2020/9/28 17:54
  * @created by oxygen
  */
@Configuration
public class RedisListenerConfig {

    @Autowired
    //@Qualifier("redisConnectionFactory0")
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
	RedisMessageListenerContainer redisMessageListenerContainer() {
        System.out.println("---- RedisListenerConfig:redisMessageListenerContainer ----");
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

}
