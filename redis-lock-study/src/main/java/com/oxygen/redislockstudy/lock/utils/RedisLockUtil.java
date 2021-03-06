package com.oxygen.redislockstudy.lock.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * RedisLock工具类
 * @author oxygen
 * @description 直接使用Redis进行分布式锁
 * @date 2020/5/21 14:59
 **/
@Component
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *Redis加锁的操作
     *
     *@param key
     *@param value
     *@return
     */
    public Boolean tryLock(String key, String value) {
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间 如果高并发的情况可能会出现已经被修改的问题  所以多一次判断保证线程的安全
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (StringUtils.isNotEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }


    /**
     *Redis解锁的操作
     *
     *@param key
     *@param value
     *  
     */
    public void unlock(String key, String value) {
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        try {
            if (StringUtils.isNotEmpty(currentValue) && currentValue.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}