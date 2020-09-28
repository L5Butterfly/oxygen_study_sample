package com.oxygen.redislockstudy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfigurations {

    private static Logger logger = LoggerFactory.getLogger(RedisConfigurations.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.redis.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;


    /**
     * 连接池配置
     *
     * @Description:
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        //jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        return jedisPoolConfig;
    }
    /**
     * redis连接的基础设置
     *
     * @Description:
     * @return
     */
    @Primary
    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        // 存储的库
        factory.setDatabase(3);
        // 设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        logger.info("redis factory3 inited finish...host:" + host + ",port:" + port);
        return factory;
    }

    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        setRedisTemplateSerializer(redisTemplate);
        logger.info("redis restTemplate3 inited finish...");
        return redisTemplate;
    }


    /////////////////////////////////// database 1
    /**
     * redis连接的基础设置1
     *
     * @Description:
     * @return
     */
    @Bean(name = "redisConnectionFactory1")
    public RedisConnectionFactory redisConnectionFactory1() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        // 存储的库
        factory.setDatabase(1);
        // 设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        logger.info("redis factory1 inited finish...host:" + host + ",port:" + port);
        return factory;
    }

    @Bean(name = "redisTemplate1")
    public RedisTemplate<String, Object> redisTemplate1(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory1());
        setRedisTemplateSerializer(redisTemplate);
        logger.info("redis restTemplate1 inited finish...");
        return redisTemplate;
    }


    /////////////////////////////////// database 0
    /**
     * redis连接的基础设置0
     *
     * @Description:
     * @return
     */
    @Bean(name = "redisConnectionFactory0")
    public RedisConnectionFactory redisConnectionFactory0() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        // 存储的库
        factory.setDatabase(0);
        // 设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        logger.info("redis factory0 inited finish...host:" + host + ",port:" + port);
        return factory;
    }

    @Bean(name = "redisTemplate0")
    public RedisTemplate<String, Object> redisTemplate0(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory0());
        setRedisTemplateSerializer(redisTemplate);
        logger.info("redis restTemplate0 inited finish...");
        return redisTemplate;
    }

    /////////////////////////////////// database 8
    /**
     * redis连接的基础设置8
     *
     * @Description:
     * @return
     */
    @Bean(name = "redisConnectionFactory8")
    public RedisConnectionFactory redisConnectionFactory8() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        // 存储的库
        factory.setDatabase(8);
        // 设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        logger.info("redis factory8 inited finish...host:" + host + ",port:" + port);
        return factory;
    }

    @Bean(name = "redisTemplate8")
    public RedisTemplate<String, Object> redisTemplate8(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory8());
        setRedisTemplateSerializer(redisTemplate);
        logger.info("redis restTemplate8 inited finish...");
        return redisTemplate;
    }

    /**
     * redis连接的基础设置10
     *
     * @Description:
     * @return
     */
    @Bean(name = "redisConnectionFactory10")
    public RedisConnectionFactory redisConnectionFactory10() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        // 存储的库
        factory.setDatabase(10);
        // 设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        logger.info("redis factory8 inited finish...host:" + host + ",port:" + port);
        return factory;
    }

    @Bean(name = "redisTemplate10")
    public RedisTemplate<String, Object> redisTemplate10(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory10());
        setRedisTemplateSerializer(redisTemplate);
        logger.info("redis restTemplate8 inited finish...");
        return redisTemplate;
    }

    /**
     * 设置序列化方式
     * 使用Jackson2JsonRedisSerialize替换默认序列化
     */
    private void setRedisTemplateSerializer(RedisTemplate<String, Object> redisTemplate) {
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
    }


    /**
     * 设置序列化方式
     */
    private void setRedisTemplateSerializer3(RedisTemplate<String, Object> redisTemplate) {
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        // 设置Key的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        // JdkSerializationRedisSerializer序列化方式;
        // JdkSerializationRedisSerializer jdkRedisSerializer=new
        // JdkSerializationRedisSerializer();
        // redisTemplate.setValueSerializer(jdkRedisSerializer);
        // redisTemplate.setHashValueSerializer(jdkRedisSerializer);
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
    }
}
