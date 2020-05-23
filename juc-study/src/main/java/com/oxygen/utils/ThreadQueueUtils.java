package com.oxygen.utils;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * <br>
 * 多线程工具-请求塞入队列,异步处理任务
 * @author empathy
 */

public class ThreadQueueUtils<T, R> {

    /**
     * 线程队列,塞入业务执行的入参数据, 数据出入顺序：先进先出。
     * LinkedBlockingQueue：一个由链表结构组成的有界队列，此队列的长度为Integer.MAX_VALUE。此队列按照先进先出的顺序进行排序。
     * https://www.cnblogs.com/WangHaiMing/p/8798709.html
     */
    @Getter
    private LinkedBlockingQueue<T> queue    = new LinkedBlockingQueue<>();


    /**
     * 异步执行业务的方法,自定义业务处理的方法
     */
    @Setter
    private Function<T, R>         function;


    /**
     * 回调函数-异步执行方法任务,返回执行结果
     */
    private Callable<List<R>>      callable = new Callable<List<R>>() {
        @Override
        public List<R> call() {
            List<R> list = Lists.newArrayList();
            for (; ; ) {
                T poll = queue.poll();
                if (poll == null) {
                    break;
                }
                //多个线程从队列里面取数据进行消费
                System.out.println("---------当前消费的数据对象："+poll);
                System.out.println("当前线程名称："+Thread.currentThread().getName());
                R r = function.apply(poll);
                if (r != null) {
                    list.add(r);
                }
            }
            return list;
        }
    };


    /**
     * 构建线程队列工具对象,对线程对象设置入参和执行方法
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> ThreadQueueUtils create(List<T> list, Function<T, R> function) {
        ThreadQueueUtils<T, R> threadQueueUtils = new ThreadQueueUtils<>();
        threadQueueUtils.getQueue().addAll(list);
        threadQueueUtils.setFunction(function);
        return threadQueueUtils;
    }


    /**
     * 创建多线程任务，处理队列中读取的数据进行处理
     * @param threadNum  开启的线程数
     * @return
     */
    public List<Future<List<R>>> start(int threadNum) throws ExecutionException, InterruptedException {
        List<Future<List<R>>> result = Lists.newArrayList();
        //定义线程池，设置线程池参数：核心线程数和处理策略等等
        ExecutorService executor = ExecutorUtils.RP_GROUP_UPDATE_POOL;
        try{
            for (int i = 0; i < threadNum; i++) {
                //开启多线程执行回调任务
                System.out.println("提交执行："+i);
                Future<List<R>> future = executor.submit(callable);
                //TODO: 注意 future.get() 方法会阻塞直到拿到结果后执行后续操作
                //List<R> rs = future.get();
                //System.out.println(String.format("线程执行结果[%s]:",i)+rs);
                result.add(future);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            // shutdown：平滑的关闭ExecutorService，
            // 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
            // 当所有提交任务执行完毕，线程池即被关闭。
            executor.shutdown();
        }
        return result;
    }


    /**
     * 获取多线程执行的结果，统一组装成一个List对象
     * @param threadNum  开启的线程数
     * @return
     */
    public List<R> startAndGet(int threadNum) {
        try {
            List<R> list = Lists.newArrayList();
            List<Future<List<R>>> start = this.start(threadNum);
            for (Future<List<R>> future : start) {
                List<R> item = future.get();
                list.addAll(item);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
