package com.oxygen.redislockstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/7 11:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "spring.config.location = classpath:application-test.yml" })
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testRedis() {
		String key = "hello";
		redisTemplate.opsForValue().set("hello", "你好");

		String res = (String) redisTemplate.opsForValue().get(key);
		System.out.println(res);
	}

	@Test
	public void testKeyOps() {

	}

	@Test
	public void testHashOps() {
		String key = "hash";
		// 单次往hash中存放一个数据
		redisTemplate.opsForHash().put(key, "1", "你好");

		Map<String, Object> map = new HashMap<>();
		map.put("2", "hello");
		map.put("3a", "china1=2");

		// 一次性向hash中存放一个map
		redisTemplate.opsForHash().putAll(key, map);

		// 获取hash下的所有key和value
		Map<String, Object> resultMap = redisTemplate.opsForHash().entries(key);
		for (String hashKey : resultMap.keySet()) {
			System.out.println(hashKey + ": " + resultMap.get(hashKey));
		}
	}

	@Test
	public void testListOps() {
		String listKey = "list";
		redisTemplate.opsForList().leftPush(listKey, "first value"); // 从list最左边插入数据
		redisTemplate.opsForList().leftPush(listKey, "second value but left");
		redisTemplate.opsForList().rightPush(listKey, 3); // 从list最右边插入数据

		List<Object> list = new ArrayList<>();
		list.add("hello");
		list.add("http://www.eknown.cn");
		list.add(23344);
		list.add(false);
		redisTemplate.opsForList().rightPushAll(listKey, list); // 从list右边批量插入数据

		long size = redisTemplate.opsForList().size(listKey);
		if (size > 0) {
			for (int i = 0; i < size -1 ; i++) {
				// 从list最左边开始读取list中的数据，注意pop会导致出栈，也就是数据被取出来了（redis中就没有这个值了）
				// 此处我们读取size-1条数据，仅留下最后一条数据
				System.out.println(i + ":" + redisTemplate.opsForList().leftPop(listKey).toString());
			}
		}
	}
}