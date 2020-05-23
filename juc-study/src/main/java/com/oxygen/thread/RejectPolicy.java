package com.oxygen.thread;

import java.util.concurrent.Executor;


/**
 * 拒绝策略
 */
public interface RejectPolicy {

    /**
     *
     * @param task
     * @param executor
     */
    void reject(Runnable task, Executor executor);


    /**
     *
     * @param task
     * @param executor
     */
    void reject(Runnable task, MyThreadPoolExecutor executor);
}
