package com.oxygen.redislockstudy.controller;

import com.oxygen.redislockstudy.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
  *
  * @author wangchao
  * @date 2020/9/28 16:53
  * @created by oxygen
  */
@RestController
@RequestMapping("")
public class RedisController {


	@Qualifier("redisTemplate3")
	@Autowired
	private RedisTemplate redisTemplate3;

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	 /**
	  * http://127.0.0.1:8888/redis/save
	  */
	 @RequestMapping("redis/save")
	 public void  testKeyOps(){
		// 测试redis操作key-value形式
		 Set<String> keySet = new HashSet<>();
		 String key1 = "name";
		 keySet.add(key1);
		 // 存储简单的key-value，并设置过期时间
		 redisTemplate.opsForValue().set(key1, "demo", 1, TimeUnit.MINUTES);

		 //定义缓存key,结构类似菜单分级结构
		 String key2 = "token:user1";
		 String key3 = "token:user2";
		 keySet.add(key2);
		 keySet.add(key3);
		 // 存取对象
		 UserDTO userDTO=new UserDTO("demo",18);
		 UserDTO userDTO2=new UserDTO("demo2",18);
		 redisTemplate.opsForValue().set(key2, userDTO,10,TimeUnit.SECONDS);
		 redisTemplate.opsForValue().set(key3, userDTO2,10,TimeUnit.SECONDS);

		 // 根据key的集合获取多个value
		 List<Object> valueList = redisTemplate.opsForValue().multiGet(keySet);
		 for (Object value : valueList) {
			 System.out.println(value);
		 }
		 redisTemplate3.opsForValue().set("111","2222");
	 }


}
