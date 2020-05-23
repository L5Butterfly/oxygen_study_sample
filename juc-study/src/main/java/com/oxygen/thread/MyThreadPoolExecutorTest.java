package com.oxygen.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *  java线程系列之自己动手写一个线程池
 */
public class MyThreadPoolExecutorTest {

    public static void main(String[] args) {

        /**
         * 定义一个线程池
         */
        Executor threadPool = new MyThreadPoolExecutor("test", 5, 10,
                new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());


        /**
         * 异步多线程计数器
         */
        AtomicInteger num = new AtomicInteger(0);


        /**
         * 多线程测试
         */
        for (int i = 0; i < 100; i++) {
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("running: " + System.currentTimeMillis() + ": " + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
