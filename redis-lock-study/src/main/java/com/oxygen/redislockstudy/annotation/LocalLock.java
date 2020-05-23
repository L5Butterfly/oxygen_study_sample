package com.oxygen.redislockstudy.annotation;


import java.lang.annotation.*;

/**
 * @author oxygen
 * @description 本地缓存锁实现
 * @date 2020/5/21 14:25
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {


    /**
     * 本地缓存锁，应用场景：前端表单重复提交，网络超时等问题
     * @return
     */
    String key() default "";
}
