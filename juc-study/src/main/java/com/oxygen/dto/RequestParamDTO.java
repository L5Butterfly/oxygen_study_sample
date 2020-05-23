package com.oxygen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: empathy
 * @description: RequestParamDTO
 * @date: 2019/11/6 10:50
 **/

@EqualsAndHashCode(callSuper=true)
@Data
public class RequestParamDTO extends BaseIdDTO{

    private String orderId;
    private String businessCode;
    private String type;
    private String title;
    private String remark;

    /**
     * 设置参数
     * @param paramDTO
     */
    public void setParam(RequestParamDTO paramDTO) {

        if(null==paramDTO){
            throw new NullPointerException("paramDTO cat`t is null");
        }
        this.orderId = paramDTO.getOrderId();
        this.businessCode = paramDTO.getBusinessCode();
        this.type = paramDTO.getType();
        this.title = paramDTO.getTitle();
        this.remark = paramDTO.getRemark();
    }
}
