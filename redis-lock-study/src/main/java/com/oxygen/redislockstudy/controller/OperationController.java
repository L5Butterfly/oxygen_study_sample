package com.oxygen.redislockstudy.controller;


import com.oxygen.redislockstudy.redislock2.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oxygen
 * @description OperationController
 * @date 2020/5/21 15:05
 **/

@RestController
@RequestMapping("/demo")
public class OperationController {


    @Autowired
    BusinessService businessService;

    @GetMapping("/test")
    public String createOrderTest() {
//        if (!businessService.decrementProductStore(1L, 1)) {
//            return "库存不足";
//        }
//        OrderMaster orderMaster = new OrderMaster();
//        //未支付
//        orderMaster.setOrderStatus(0);
//        //未支付
//        orderMaster.setPayStatus(0);
//        orderMaster.setBuyerName("张三");
//        orderMaster.setBuyerAddress("湖南长沙");
//        orderMaster.setBuyerPhone("18692794847");
//        orderMaster.setOrderAmount(BigDecimal.ZERO);
//        orderMaster.setCreateTime(DateUtils.getCurrentDate());
//        orderMaster.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
//        orderMasterService.insert(orderMaster);

        for (int i = 0; i < 20; i++) {
            Thread item=new Thread(()->{
                businessService.decrementProductStore(1L, 1);
            });
            item.start();
        }
        
        return "执行后续操作逻辑";
    }
}



