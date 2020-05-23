package com.oxygen.rpc.client;

import com.oxygen.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description PRC调用的请求过程封装
 * @Date 2020/5/18 16:38
 * @Created by oxygen
 */
public class RpcNetTransport {

    /**
     * 服务地址
     */
    private String host;

    /**
     * 服务端口
     */
    private int port;


    /**
     * 构造方式赋值
     * @param host
     * @param port
     */
    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * Socket方法建立长链接发生请求
     * @param request
     * @return
     */
    public Object send(RpcRequest request){
        Socket socket;
        Object result=null;
        ObjectOutputStream outputStream=null;
        ObjectInputStream inputStream=null;

        try {
            //建立连接
            socket=new Socket(host,port);
            //网络socket
            outputStream =new ObjectOutputStream(socket.getOutputStream());
            //序列化()
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream=new ObjectInputStream(socket.getInputStream());
            result=inputStream.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }
}
