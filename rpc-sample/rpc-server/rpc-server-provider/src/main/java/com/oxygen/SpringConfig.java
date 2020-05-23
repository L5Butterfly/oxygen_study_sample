package com.oxygen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @Description spring-context 使用注解的方式注入容器管理
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */
@Configuration
@ComponentScan(basePackages = "com.oxygen")
public class SpringConfig {


    /**
     * 单例注入初始化Bean,返回接口服务
     * @return
     */
    @Bean(name="gpRpcServer")
    public GpRpcServer gpRpcServer(){
        return new GpRpcServer(8080);
    }
}
