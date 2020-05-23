package com.oxygen.rpc.client;

import com.oxygen.IPaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author oxygen
 * @description  客户端服务启动
 * @date 2020/5/18 16:37
 **/
public class AppStart {

    public static void main( String[] args ) {
        /*RpcProxyClient rpcProxyClient=new RpcProxyClient();

        IHelloService iHelloService=rpcProxyClient.clientProxy
                (IHelloService.class,"localhost",8080);

        String result=iHelloService.sayHello("Mic");
        System.out.println(result);*/


        /**
         * Spring注解的方法加载启动，加载Bean和通过上下文获取Bean对象，容器式单例
         */
        ApplicationContext context=new
                AnnotationConfigApplicationContext(SpringConfig.class);

        //获取代理服务
        RpcProxyClient rpcProxyClient=context.getBean(RpcProxyClient.class);

        /*IHelloService iHelloService=rpcProxyClient.clientProxy
                (IHelloService.class,"localhost",8080);*/


        /**
         * IPaymentService只是服务的接口，不能直接实例化，需要用JDK的动态代理生成实例
         * 类似Mybatis Mapper接口的使用
         */
        IPaymentService iPaymentService=rpcProxyClient.clientProxy(IPaymentService.class,
                "localhost",8080);

        iPaymentService.doPay();
    }
}
