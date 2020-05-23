package com.oxygen.design.biz.impl;

import com.alibaba.fastjson.JSON;
import com.oxygen.design.BsResult;
import com.oxygen.design.biz.RequestLogBiz;
import com.oxygen.dto.RequestParamDTO;
import com.oxygen.utils.bussines.dto.DataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestLogBizImpl implements RequestLogBiz {

    @Override
    public void saveLog(DataDTO data) {
        log.info("执行日志保留逻辑");
        log.info("[请求入参打印],data={}", JSON.toJSON(data));
    }

    @Override
    public <T> void saveLog(T param, String methodName) {
        log.info("执行日志保留逻辑");
        log.info("[请求入参打印],param={}", JSON.toJSON(param));
    }


    @Override
    public <T> BsResult<T> checkLogParam(RequestParamDTO param) {
        log.info("请求入参校验");
        log.info("[请求入参打印],param={}", JSON.toJSON(param));
        BsResult result = BsResult.builder().build();
        result.setResult(param);
        return result;
    }


}
