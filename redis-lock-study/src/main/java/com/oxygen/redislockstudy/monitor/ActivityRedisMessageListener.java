package com.oxygen.redislockstudy.monitor;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.oxygen.redislockstudy.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.List;

 /**
  * 类信息注释
  * @author Administrator
  * @date 2020/9/28 17:33
  * @created by oxygen
  */
@Component
@Slf4j
public class ActivityRedisMessageListener extends KeyExpirationEventMessageListener {

    public ActivityRedisMessageListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
        RedisConnectionFactory connectionFactory = listenerContainer.getConnectionFactory();
    }

    /**
     *  需要监听的key
     */
    private static final List<String> NEED_LISTEN_KEYS = ImmutableList.of(
            "token:user1","token:user2"
    );

    @Override
    public void onMessage(Message message, byte[] pattern) {

        log.info("******************redis listener*************");
        log.info("******************context={}", JSON.toJSON(message));
        String key = new String(message.getBody(), StandardCharsets.UTF_8);

        if (key.contains("lock") || NEED_LISTEN_KEYS.stream().noneMatch(key::startsWith)) {
            return;
        }

        if (key.startsWith("token:user1")) {
            // 车辆出行订单 支付超时取消订单
            SpringUtil.getBean(CancelOrderMessageHandler.class).handle(message, pattern);
        }
        if (key.startsWith("token:user2")) {
            // 车辆出行订单 支付超时取消订单
            SpringUtil.getBean(CancelOrderMessageHandler.class).handle(message, pattern);
        }
    }

}
