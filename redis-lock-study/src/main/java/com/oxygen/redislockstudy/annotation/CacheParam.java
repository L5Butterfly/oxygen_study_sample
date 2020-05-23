package com.oxygen.redislockstudy.annotation;


import java.lang.annotation.*;

/**
 *
 * 锁的参数
 * @author oxygen
 * @description 请求接口动态入参
 * @date 2020/5/21 10:57
 **/

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {


    /**
     * 字段名称
     * @return
     */
    String name() default "";

}
