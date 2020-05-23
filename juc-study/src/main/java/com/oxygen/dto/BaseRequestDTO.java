package com.oxygen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author: empathy
 * @description: BaseRequestDTO
 * @date: 2019/11/6 11:15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseRequestDTO extends BaseDTO implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9052846749689710323L;

    /**
     * 业务请求ID
     */
    private String requestId;
}
