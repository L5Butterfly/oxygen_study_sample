package com.oxygen.redislockstudy.monitor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;

/**
  * 类信息注释
  * @author Administrator
  * @date 2020/9/28 17:46
  * @created by oxygen
  */
@Service
@Slf4j
public class CancelOrderMessageHandler implements IActivityRedisMessageHandler {
	@Override
	public void handle(Message message, byte[] pattern) {

		log.info("-------redis失效监听中----------");
		log.info(JSON.toJSONString(message));
	}
}
