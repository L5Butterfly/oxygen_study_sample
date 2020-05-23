package com.oxygen.redislockstudy.redislock2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 * @author oxygen
 * @description RedisConfig
 * @date 2020/5/21 14:55
 **/
@Configuration
public class RedisConfig {


    /**
     * 配置RedisConfig属性、如果需要使用FastJSON来序列化你的对象
     * @param redisConnectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public RedisTemplate initRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
