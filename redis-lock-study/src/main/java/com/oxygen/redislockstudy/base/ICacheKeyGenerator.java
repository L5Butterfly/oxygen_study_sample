package com.oxygen.redislockstudy.base;


import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 * @author oxygen
 * @description  锁key的生成策略
 * @date 2020/5/21 11:01
 **/
public interface ICacheKeyGenerator {


    /**
     * 获取AOP参数,生成指定缓存Key
     * @param pjp  AOP切面参数
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
