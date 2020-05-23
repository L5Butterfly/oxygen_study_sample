package com.oxygen.redislockstudy.aspect;


import com.oxygen.redislockstudy.annotation.CacheLock;
import com.oxygen.redislockstudy.base.ICacheKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Lock拦截器（AOP）
 *
 * @author oxygen
 * @description redis 方案
 * @date 2020/5/21 11:07
 **/

@Aspect
@Configuration
public class LockMethodInterceptor {

    private static final Long LOCK_SUCCESS = 1L;
    private static final Long LOCK_EXPIRED = -1L;

    @Autowired
    private final StringRedisTemplate lockRedisTemplate;

    @Autowired
    private final ICacheKeyGenerator cacheKeyGenerator;


//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    public LockMethodInterceptor(@Qualifier("customRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }


    /**
     * 构造传入Redis模板和Key生成策略
     * @param lockRedisTemplate
     * @param cacheKeyGenerator
     */
    //@Autowired
    public LockMethodInterceptor(StringRedisTemplate lockRedisTemplate, ICacheKeyGenerator cacheKeyGenerator) {
        this.lockRedisTemplate = lockRedisTemplate;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }


    /**
     * 切面实现接口幂等操作-分布式锁控制并发
     * @param pjp
     * @return
     */
    @Around("execution(public * *(..)) && @annotation(com.oxygen.redislockstudy.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key can't be null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        try {
            //key不存在才能设置成功
            final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "");
            if (success) {
                lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            } else {
                //按理来说 我们应该抛出一个自定义的 CacheLockException 异常;
                throw new RuntimeException("请勿重复请求");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            //如果演示的话需要注释该代码;实际应该放开
            lockRedisTemplate.delete(lockKey);
        }
    }

}
