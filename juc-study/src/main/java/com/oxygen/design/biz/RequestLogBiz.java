package com.oxygen.design.biz;


import com.oxygen.design.BsResult;
import com.oxygen.dto.RequestParamDTO;
import com.oxygen.utils.bussines.dto.DataDTO;

/**
 * @author: empathy
 * @description: 请求日志
 * @date: 2019/11/6 14:37
 **/
public interface RequestLogBiz {

    /**
     * 保存请求日志
     * @param data
     */
    void saveLog(DataDTO data);


    /**
     * 泛型的定义，更好的扩展
     * @param param
     * @param methodName
     * @param <T>
     */
    <T> void saveLog(T param, String methodName);


    /**
     * 参数校验
     * @param param 请求对象继承基类BaseRequestDTO
     * @param <T>
     * @return
     */
    <T> BsResult<T> checkLogParam(RequestParamDTO param);
}
