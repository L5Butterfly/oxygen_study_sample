package com.oxygen.rpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Spring容器的方式管理
 * @Date 2020/5/18 16:38
 * @Created by oxygen
 */
@Configuration
public class SpringConfig {

    @Bean(name="rpcPRoxyClient")
    public RpcProxyClient proxyClient(){
        return new RpcProxyClient();
    }
}
