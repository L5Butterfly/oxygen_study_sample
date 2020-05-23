package com.oxygen.utils;



import com.oxygen.utils.bussines.dto.DataDTO;
import com.oxygen.utils.bussines.impl.TestBizImpl;
import com.oxygen.utils.bussines.services.ITestBiz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 多线程并发提交获取数据结果
 * 场景：接口分批次查询，提供查询效率。
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.定义消费的数据对象列表
        List<DataDTO> dataList=new ArrayList<>();
        for (int i = 0; i <100; i++) {
            DataDTO item=new DataDTO("a"+i,"a"+i,"a"+i);
            dataList.add(item);
        }
        //2.定义单个数据对象的业务处理逻辑
        ITestBiz bizService= new TestBizImpl();

        //3.利用多线程并发执行获取消费的数据结果（指定线程执行数不超过核心线程数）
        long startTime=System.currentTimeMillis();   //获取开始时间
        List list = ThreadQueueUtils.create(dataList, bizService.getFunction()).startAndGet(20);
        long endTime=System.currentTimeMillis();   //获取开始时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        //todo:为什么这个线程池的核心线程和最大线程，消费的线程总是小于核心线程的，不知道为什么???
        System.out.println("执行结果:");
        System.out.println(list);

    }
}
