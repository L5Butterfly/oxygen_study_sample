package com.oxygen;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Description  Socket编程，RPC面向过程调用的通信协议
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */
public class ProcessorHandler implements Runnable{


    /**
     * 网络的Socket服务
     */
    private Socket socket;


    /**
     * RPC对外暴露的接口信息，享元模式
     */
    private Map<String,Object> handlerMap;


    /**
     * 构造方法传参
     * @param socket
     * @param handlerMap
     */
    public ProcessorHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }


    /**
     * dubbo底层使用socket建立长连接，发送、接收数据的形式进行通信，
     * 结合使用apache mina框架，使用IoSession.write()方法，这个方法是一个异步的调用。
     * 即对于当前线程来说，只需要将请求发送出去。就可以继续向后执行了。
     *
     * 基本原理
     * https://blog.csdn.net/wj1607162253/article/details/77948376
     * dubbo底层使用socket建立长连接，发送、接收数据的形式进行通信
     */
    @Override
    public void run() {
        //输入流和输出流
        ObjectInputStream objectInputStream=null;
        ObjectOutputStream objectOutputStream=null;

        try {
            objectInputStream=new ObjectInputStream(socket.getInputStream());

            //输入流中应该有什么东西？
            //请求哪个类，方法名称、参数
            RpcRequest rpcRequest=(RpcRequest)objectInputStream.readObject();
            Object result=invoke(rpcRequest); //反射调用本地服务
            //将接口写会客户端调用方
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 使用反射技术，反射调用本地服务方法
     * @param request
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //反射调用
        String serviceName=request.getClassName();
        String version=request.getVersion();
        //增加版本号的判断
        if(!StringUtils.isEmpty(version)){
            serviceName+="-"+version;
        }

        Object service=handlerMap.get(serviceName);
        if(service==null){
            throw new RuntimeException("service not found:"+serviceName);
        }
        //拿到客户端请求的参数
        Object[] args=request.getParameters();
        Method method=null;
        if(args!=null) {
            //获得每个参数的类型
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            //跟去请求的类进行加载
            Class clazz=Class.forName(request.getClassName());
            //sayHello, saveUser找到这个类中的方法
            method=clazz.getMethod(request.getMethodName(),types);
        }else{
            //跟去请求的类进行加载
            Class clazz=Class.forName(request.getClassName());
            //sayHello, saveUser找到这个类中的方法
            method=clazz.getMethod(request.getMethodName());
        }

        //HelloServiceImpl 进行反射调用
        Object result=method.invoke(service,args);
        return result;
    }
}
