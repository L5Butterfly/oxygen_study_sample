package com.oxygen.design.common.offer;


import com.oxygen.design.BsResult;
import com.oxygen.design.common.template.BaseOperateTemplate;
import com.oxygen.dto.LogDO;
import com.oxygen.dto.RequestParamDTO;
import lombok.extern.slf4j.Slf4j;


/**
 * 模板方法模式
 * @author: empathy
 * @description: 
 * @date: 2019/11/7 10:54
 **/
@Slf4j
public abstract class BaseOneTemplate<T extends RequestParamDTO> extends BaseOperateTemplate<T,String> {

    @Override
    protected BsResult<String> logicMethod(T param, LogDO logDO, String mode) {
        return null;
    }

    @Override
    protected void afterExecute(T param, BsResult<String> result) {

    }

    @Override
    protected void beforeExecute(T param) {

    }

    @Override
    protected BsResult<String> logicInputCheck(T param) {
        return null;
    }
}
