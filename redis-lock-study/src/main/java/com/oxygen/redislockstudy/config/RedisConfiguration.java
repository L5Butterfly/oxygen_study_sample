package com.oxygen.redislockstudy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
  * redis配置 <br/>
  * 主要是配置Redis的序列化规则，替换默认的jdkSerializer  <br/>
  *  key的序列化规则用StringRedisSerializer  <br/>
  *  value的序列化规则用Jackson2JsonRedisSerializer  <br/>
  *
  * @author wangchao
  * @date 2020/9/28 14:33
  * @created by oxygen
  */
@Configuration
public class RedisConfiguration {

//	@Bean(name = "redisConnectionFactory0")
//	public JedisConnectionFactory redisConnectionFactory0(JedisConnectionFactory jedisConnectionFactory) {
//		jedisConnectionFactory.setDatabase(0);
//		return jedisConnectionFactory;
//	}

	 @Bean
	 public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory0) {
		 RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		 redisTemplate.setConnectionFactory(redisConnectionFactory0);

		 // 使用Jackson2JsonRedisSerialize替换默认序列化
		 Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		 objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		 jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		 // 设置key和value的序列化规则（String）
		 redisTemplate.setKeySerializer(new StringRedisSerializer());
		 redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

		 //设置key和value的序列化规则(Hash)
		 redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		 redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

		 redisTemplate.afterPropertiesSet();
		 return redisTemplate;
	 }
}
