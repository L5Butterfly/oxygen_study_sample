package com.oxygen.rpc.client;


import com.oxygen.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * RemoteInvocationHandler--代理触发类，invoke（）方法是调用接口方法触发的执行逻辑。
 * @Description JDK动态代理方式封装远程调用RPC的底层过程
 * @Date 2020/5/18 16:38
 * @Created by oxygen
 */
public class RemoteInvocationHandler implements InvocationHandler {

    /**
     * 服务地址
     */
    private String host;


    /**
     * 服务端口
     */
    private int port;

    /**
     * 调用的服务地址和端口
     * @param host
     * @param port
     */
    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * 客户端调用RPC暴露的接口指定的方法触发的逻辑。
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //请求会进入到这里
        System.out.println("come in");
        //请求数据的包装
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        // rpcRequest.setVersion("v2.0");
        //远程通信
        RpcNetTransport netTransport=new RpcNetTransport(host,port);
        Object result=netTransport.send(rpcRequest);

        return result;
    }
}
