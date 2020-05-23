package com.oxygen.rocketmqtest.controller;


import com.alibaba.fastjson.JSON;
import com.oxygen.rocketmqtest.entity.UserDTO;
import com.oxygen.rocketmqtest.jms.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("mq")
public class Controller {

    @Autowired
    Producer producer ;


    /**
     * http://127.0.0.1:8888/mq/message
     * @throws Exception
     */
    @GetMapping("/message")
    public  String  message() throws Exception {
        List<UserDTO> data = createData(10);
        data.forEach(item-> {
            try {
                sendMqMessage(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            }
        });
        //同步
        //sync();
        //异步
        //async();
        //单项发送
        //oneWay();

        return "success";
    }


    /**
     *
     * 生成数据
     * @return
     */
    private List<UserDTO> createData(int num){
        List<UserDTO>  data = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            UserDTO item=new UserDTO("test",i,"内容："+i);
            data.add(item);
        }
        return data;
    }


    /**
     * sendMqMessage
     * @param userDTO
     */
    private void sendMqMessage(UserDTO userDTO) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //创建消息
        Message message = new Message("topic_family", JSON.toJSONString(userDTO).getBytes());
        //同步发送消息
        SendResult sendResult = producer.getProducer().send(message);
        log.info("Product-同步发送-Product信息={}", sendResult);
    }




    /**
     * 1、同步发送消息
     */
    private  void sync() throws Exception {
        //创建消息
        Message message = new Message("topic_family", ("  同步发送  ").getBytes());
        //同步发送消息
        SendResult sendResult = producer.getProducer().send(message);
        log.info("Product-同步发送-Product信息={}", sendResult);
    }
    /**
     * 2、异步发送消息
     */
    private  void async() throws Exception {
        //创建消息
        Message message = new Message("topic_family", ("  异步发送  ").getBytes());
        //异步发送消息
        producer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Product-异步发送-输出信息={}", sendResult);
            }
            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
                //补偿机制，根据业务情况进行使用，看是否进行重试
            }
        });
    }
    /**
     * 3、单项发送消息
     */
    private  void oneWay() throws Exception {
        //创建消息
        Message message = new Message("topic_family", (" 单项发送 ").getBytes());
        //同步发送消息
        producer.getProducer().sendOneway(message);
    }

}
