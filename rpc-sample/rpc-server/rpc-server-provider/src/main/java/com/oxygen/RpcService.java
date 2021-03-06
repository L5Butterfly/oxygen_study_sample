package com.oxygen;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description  RpcService服务
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */

@Target(ElementType.TYPE) //类/接口
@Retention(RetentionPolicy.RUNTIME)
@Component //被spring进行扫描？
public @interface RpcService {

    /**
     * 拿到服务的接口
     * @return
     */
    Class<?> value();

    /**
     * 版本号
     */
    String version() default "";

}
