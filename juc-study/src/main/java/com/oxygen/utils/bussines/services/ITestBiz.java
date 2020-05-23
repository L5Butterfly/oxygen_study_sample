package com.oxygen.utils.bussines.services;


import com.oxygen.utils.bussines.dto.DataDTO;
import java.util.List;
import java.util.function.Function;


/**
 * 业务处理的类，多线程查询数据，一期返回查询结果给前端
 * 提高用户体验，减少前端查询响应时间
 */
public interface ITestBiz {

    /**
     * 获取查询条件的数据，执行数据库item操作
     * @return
     */
    List<DataDTO> doData();


    /**
     * item执行数据库查询结果
     * 入参:T(DataDTO),出参：R(String)
     * @return
     */
    Function<DataDTO, String> getFunction();

}
