package com.oxygen.design.biz;


/**
 * @author: empathy
 * @description: 业务处理接口
 * @date: 2019/11/6 14:41
 **/
public interface RequestBusinessBiz {


    /**
     * 业务处理
     */
    void  doSomething();

    /**
     * 业务处理
     * @param param
     * @param <T>
     */
    <T> void doBusiness(T param);
}
