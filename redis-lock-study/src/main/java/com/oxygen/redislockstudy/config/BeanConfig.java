package com.oxygen.redislockstudy.config;

import com.oxygen.redislockstudy.base.ICacheKeyGenerator;
import com.oxygen.redislockstudy.base.LockKeyGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public ICacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGeneratorImpl();
    }
}
