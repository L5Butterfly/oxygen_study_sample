package com.oxygen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AppStart
 * RPC服务提供方的启动配置
 */
public class AppStart {
    public static void main( String[] args ) {
      /* IHelloService helloService=new HelloServiceImpl();
       RpcProxyServer proxyServer=new RpcProxyServer();
       proxyServer.publisher(helloService,8080);*/

        /**
         * 通过注解的方式启动
         */
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) context).start();
    }
}
