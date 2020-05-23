package com.oxygen;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description  GpRpcServer初始化执行
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */
@Component
public class GpRpcServer implements ApplicationContextAware,InitializingBean {


    /**
     * 线程池，待缓存的线程池
     */
    ExecutorService executorService= Executors.newCachedThreadPool();


    /**
     * 存放对外暴露的接口
     */
    private Map<String,Object> handlerMap=new HashMap();


    /**
     * 暴露服务的端口
     */
    private int port;


    /**
     *
     * @param port
     */
    public GpRpcServer(int port) {
        this.port = port;
    }


    /**
     * 容器初始化执行,启动服务端口监听初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(port);
            while(true){//不断接受请求
                System.out.println("服务监听开始，监听中........");
                Socket socket=serverSocket.accept();//BIO
                //每一个socket 交给一个processorHandler来处理
                System.out.println("服务监听开始-----请求进入，使用线程异步处理");
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置spring上下文，容器中获取带注解的服务类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,Object> serviceBeanMap=applicationContext.getBeansWithAnnotation(RpcService.class);
        if(!serviceBeanMap.isEmpty()){
            for(Object serviceBean:serviceBeanMap.values()){
                //拿到注解
                RpcService rpcService=serviceBean.getClass().getAnnotation((RpcService.class));
                String serviceName=rpcService.value().getName();//拿到接口类定义
                String version=rpcService.version(); //拿到版本号
                if(!StringUtils.isEmpty(version)){
                    serviceName+="-"+version;
                }
                System.out.println("handlerMap，加载注入的接口服务："+serviceName);
                handlerMap.put(serviceName,serviceBean);
            }
        }
    }
}
