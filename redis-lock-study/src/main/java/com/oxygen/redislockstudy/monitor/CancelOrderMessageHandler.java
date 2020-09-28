package com.oxygen.redislockstudy.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;

/**
  * redis key 失效需要触发的业务逻辑，比如15分钟内订单自动取消
  * @author wangchao
  * @date 2020/9/28 17:46
  * @created by oxygen
  */
@Service
@Slf4j
public class CancelOrderMessageHandler implements IActivityRedisMessageHandler {
	@Override
	public void handle(Message message, byte[] pattern) {
		log.info("-------redis失效监听中----------");
		String msg=message.toString();
		log.info("key="+msg);
	}
}
