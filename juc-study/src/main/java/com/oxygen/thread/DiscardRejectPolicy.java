package com.oxygen.thread;

import java.util.concurrent.Executor;


/**
 * 线程池执行丢弃策略
 */
public class DiscardRejectPolicy implements RejectPolicy{

    @Override
    public void reject(Runnable task, Executor executor) {
        // do nothing
        System.out.println("Executor discard one task");
    }

    @Override
    public void reject(Runnable task, MyThreadPoolExecutor executor) {
        // do nothing
        System.out.println("MyThreadPoolExecutor discard one task");

    }
}
