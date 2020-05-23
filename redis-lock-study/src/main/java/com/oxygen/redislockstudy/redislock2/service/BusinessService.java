package com.oxygen.redislockstudy.redislock2.service;


import com.oxygen.redislockstudy.redislock2.utils.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author oxygen
 * @description BusinessService
 * @date 2020/5/21 15:03
 **/
@Service
@Slf4j
public class BusinessService {


    @Autowired
    RedisLockUtil redisLockUtil;


    /**
     * 库存扣减操作，防止并发
     * @param productId
     * @param productQuantity
     * @return
     */
    public boolean decrementProductStore(Long productId, Integer productQuantity) {
        String key = "dec_store_lock_" + productId+"_"+productQuantity;
        long time = System.currentTimeMillis();
        try {
            //如果加锁失败
            if (!redisLockUtil.tryLock(key, String.valueOf(time))) {
                log.error("锁被占用，获取锁失败");
                return false;
            }
            //执行库存扣减操作（模拟并发）
            //Thread.sleep(5000);
            log.info("执行库存扣减操作-----------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //解锁
            log.info("释放锁");
            redisLockUtil.unlock(key, String.valueOf(time));
        }
        return true;

    }
}
