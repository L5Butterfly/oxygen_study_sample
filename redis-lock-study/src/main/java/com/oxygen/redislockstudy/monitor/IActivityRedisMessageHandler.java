package com.oxygen.redislockstudy.monitor;

import org.springframework.data.redis.connection.Message;

 /**
  *  Redis缓存失效监听器
  * @author wangchao
  * @date 2020/9/28 17:29
  * @created by oxygen
  */
public interface IActivityRedisMessageHandler {

    /**
     * 监听处理
     * @author wangchao 
     * @date 2020/9/28
     * @param message
     * @param pattern
     * @return void
     */
    void handle(Message message, byte[] pattern);

}
