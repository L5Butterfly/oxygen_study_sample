package com.oxygen.redislockstudy.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
 * @author oxygen
 * @description  分布式锁实现方案：Redis和Zk等中间件实现
 * @date 2020/5/21 10:48
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    /**
     * redis 锁key的前缀
     * @return redis 锁key的前缀
     */
    String prefix() default "";


    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 5;


    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *  key 的生成规则是自己定义的，如果通过表达式语法自己得去写解析规则还是比较麻烦的，所以依旧是用注解的方式
     *
     * @return String
     */
    String delimiter() default ":";

}
