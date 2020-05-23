package com.oxygen.design.common.template;



import com.oxygen.design.BsResult;
import com.oxygen.design.biz.RequestBusinessBiz;
import com.oxygen.design.biz.RequestLogBiz;
import com.oxygen.dto.LogDO;
import com.oxygen.dto.RequestParamDTO;

import javax.annotation.Resource;


/**
 * @author: empathy
 * @description: 模板模式,定义业务处理逻辑基础骨架框架
 * @date: 2019/11/6 14:47
 **/
public abstract class BaseOperateTemplate <T extends RequestParamDTO,R>{

    @Resource
    private RequestLogBiz requestLogBiz;

    @Resource
    private RequestBusinessBiz requestBusinessBiz;


    /**
     * 定义模板执行逻辑和基础执行骨架
     * @param param
     * @param mode
     * @return
     */
    protected BsResult<R> execute(T param, String mode) {

        /**
         * 请求公共入参的校验和自定义参数校验
         */
        BsResult<R> result = checkInput(param);
        if (!result.isSuccess()) {
            return result;
        }
        //获取数据库操作记录
        LogDO logDO =new LogDO();
        requestBusinessBiz.doBusiness(param);

        //特殊逻辑执行
        if(isTagFlag(param,mode)){

        }
        //执行前逻辑
        beforeExecute(param);
        //业务逻辑
        result = logicMethod(param, logDO, mode);
        //执行后逻辑
        afterExecute(param,result);
        return result;
    }


    /**
     * 主方法业务逻辑,由继承的子类取去实现
     * @param param
     * @param logDO
     * @param mode
     * @return
     */
    protected abstract BsResult<R> logicMethod(T param, LogDO logDO, String mode);


    /**
     * 主方法执行前的逻辑处理,由继承的子类取去实现
     * @param param
     * @param result
     */
    protected abstract void afterExecute(T param, BsResult<R> result);


    /**
     * 主方法执行后的逻辑处理,由继承的子类取去实现
     * @param param
     */
    protected abstract void beforeExecute(T param);


    /**
     * 不同操作业务入参校验
     * @param param
     * @return
     */
    protected abstract BsResult<R> logicInputCheck(T param);


    /**
     * 钩子函数，选择性执行特殊逻辑
     * @param param
     * @param mode
     * @return
     */
    private boolean isTagFlag(T param, String mode) {
        return false;
    }


    /**
     * 校验请求入参
     * @param param
     * @return
     */
    protected  BsResult<R> checkInput(T param){
        // 业务参数校验
        BsResult<R> result = requestLogBiz.checkLogParam(param);
        if (!result.isSuccess()) {
            return result;
        }
        BsResult<R> rBsResult = logicInputCheck(param);
        return rBsResult;
    }

}
