package com.oxygen.design.biz.impl;

import com.alibaba.fastjson.JSON;
import com.oxygen.design.biz.RequestBusinessBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestBusinessBizImpl implements RequestBusinessBiz {

    @Override
    public void doSomething() {
        log.info("执行业务处理");
    }

    @Override
    public <T> void doBusiness(T param) {
        log.info("执行业务处理");
        log.info("[业务请求入参],param={}", JSON.toJSON(param));
    }
}
