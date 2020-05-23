package com.oxygen.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @Description JDK的方式，基于对外暴露的服务接口生成代理的调用过程的代理对象
 * @Date 2020/5/18 16:38
 * @Created by oxygen
 */
public class RpcProxyClient {


    /**
     * 创建代理类-基于对外暴露的服务接口生成代理的调用过程的代理对象
     * @param interfaceCls   暴露的接口
     * @param host  RPC服务调用的地址
     * @param port RPC服务调用的端口
     * @param <T>  返回泛型对象
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls},new RemoteInvocationHandler(host,port));
    }
}
