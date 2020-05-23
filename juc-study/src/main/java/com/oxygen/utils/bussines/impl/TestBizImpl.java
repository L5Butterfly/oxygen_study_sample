package com.oxygen.utils.bussines.impl;



import com.oxygen.utils.bussines.dto.DataDTO;
import com.oxygen.utils.bussines.services.ITestBiz;

import java.util.List;
import java.util.function.Function;


/**
 * @author oxygen
 * @description TestBizImpl
 * @date 2020/5/19 15:31
 **/
public class TestBizImpl implements ITestBiz {


    /**
     * List请求的批量数据
     * @return
     */
    @Override
    public List<DataDTO> doData() {
        return null;
    }


    /**
     * DataDTO-item入参,执行数据库操作获取数据
     * @return
     */
    @Override
    public Function<DataDTO, String> getFunction() {
        return item->{
            //业务执行方法
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(item.getX());
            return item.getZ();
        };
    }
}
