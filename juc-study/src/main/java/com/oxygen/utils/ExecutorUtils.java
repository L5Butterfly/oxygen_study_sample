package com.oxygen.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 * 线程池初始化配置
 * @author empathy
 */
public class ExecutorUtils {

    /**
     * 线程池1：RP_GROUP_UPDATE_POOL
     * java.util.concurrent包下的新类。LinkedBlockingQueue就是其中之一，是一个阻塞的线程安全的队列，底层采用链表实现。
     * LinkedBlockingQueue构造的时候若没有指定大小，则默认大小为Integer.MAX_VALUE，当然也可以在构造函数的参数中指定大小。LinkedBlockingQueue不接受null。
     * 添加元素的方法有三个：add,put,offer,且这三个元素都是向队列尾部添加元素的意思。
     * 区别:
     * add方法在添加元素的时候，若超出了度列的长度会直接抛出异常：
     */
    public static ExecutorService RP_GROUP_UPDATE_POOL = new ThreadPoolExecutor(5, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().build(),
            new ThreadPoolExecutor.CallerRunsPolicy());



    public static ExecutorService RP_GROUP_UPDATE_POOL3 = new ThreadPoolExecutor(30, 30,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("rp_group_update_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 线程池2：TWO_POOL
     */
    public static ExecutorService TASK_SELECT_DISPATCH_POOL = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("task-dispatch-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 线程池3：THREE_POOL
     */
    public static ExecutorService SKYLINE_LOCK_POOL = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("skyline_lock_%d")
                    .build());


    /**
     * 线程池4：FOUR_POOL
     */
    public static ExecutorService COMMON_POOL = new ThreadPoolExecutor(20, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder()
                    .setNameFormat("common_%d")
                    .build());
}
