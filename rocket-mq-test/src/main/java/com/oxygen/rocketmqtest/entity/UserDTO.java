package com.oxygen.rocketmqtest.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO  implements Serializable {

    private static final long serialVersionUID = 7386844046854684113L;


    private String name;

    private int age;

    private String remark;


    public UserDTO(String name, int age, String remark) {
        this.name = name;
        this.age = age;
        this.remark = remark;
    }
}
