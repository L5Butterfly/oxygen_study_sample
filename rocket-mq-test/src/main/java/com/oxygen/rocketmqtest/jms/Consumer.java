package com.oxygen.rocketmqtest.jms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oxygen.rocketmqtest.entity.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author xub
 * @Description: 消费者类
 * @date 2019/6/29 下午8:51
 */
@Slf4j
@Component
public class Consumer {


    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;

    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "test_consumer";

    /**
     *  通过构造函数 实例化对象
     */
    public Consumer() throws MQClientException {

        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(JmsConfig.TOPIC, "*");

        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> {

            // msgList中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
                for(Message msg:msgList) {
                    try {
                        //消费者获取消息 这里只输出 不做后面逻辑处理
                        String body = new String(msg.getBody(), "utf-8");
                        //3、获取重试次数
                        int count = ((MessageExt) msg).getReconsumeTimes();
                        log.info("当前消费重试次数为 = {}", count);
                        UserDTO userDTO = JSON.parseObject(body, UserDTO.class);
                        if(userDTO.getAge()==2){
                            log.info("构建失败的消费信息"+userDTO.toString()+"msg:"+JSON.toJSONString(msg));
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                        log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}",msg.getTopic(),body);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

        });

        consumer.start();
        System.out.println("消费者 启动成功=======");
    }

}
