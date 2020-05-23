package com.oxygen.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author: empathy
 * @description:  serializable
 * @date: 2019/11/6 10:26
 **/

@Data
public class BaseDTO implements Serializable {

    /**
     * 生成序列ID快捷键，选择dto按 alt+enter 自动添加序列
     */
    private static final long serialVersionUID = -5033327929283537400L;

    /**
     * 标识一次请求,不能重复
     */
    private   String trackId;

}
