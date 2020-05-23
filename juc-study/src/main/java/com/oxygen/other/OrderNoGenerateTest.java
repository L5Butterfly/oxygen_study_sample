package com.oxygen.other;


import com.google.common.collect.Maps;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: empathy
 * @description：订单生成测试
 * @date: 2019/9/17 9:47
 **/
public class OrderNoGenerateTest {

    public static void main(String[] args) {
       

        Map<String,Integer> orderNoMap= Maps.newHashMap();
        Set<String> orderSet=new HashSet<>();

        for (int i = 0; i <1000000 ; i++) {
            String order = SeqNo.generateSeqNo();
            boolean add = orderSet.add(order);
            System.out.println(i);
            if (add==false){
                throw new RuntimeException("生成订单重复");
            }
            orderNoMap.put(order,i);
        }
        System.out.println("测试完成，无重复订单号");
    }
}
