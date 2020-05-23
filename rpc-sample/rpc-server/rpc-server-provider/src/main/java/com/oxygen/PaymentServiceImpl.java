package com.oxygen;

/**
 * @Description
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */

@RpcService(IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService{
    @Override
    public void doPay() {
        System.out.println("执行doPay方法");
    }
}
