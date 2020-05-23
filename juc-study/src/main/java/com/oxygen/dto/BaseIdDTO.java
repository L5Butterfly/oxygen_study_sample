package com.oxygen.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author: empathy
 * @description: BaseIdDTO
 * @date: 2019/11/6 10:36
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseIdDTO extends BaseDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3169352678639054251L;


    /**
     * 数据CURD基础操作，标识ID
     */
    private Long id;
}
