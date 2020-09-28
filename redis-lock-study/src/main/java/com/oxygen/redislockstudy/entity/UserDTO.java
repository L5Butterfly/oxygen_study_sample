package com.oxygen.redislockstudy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author oxygen
 * @description UserDTO
 * @date 2020/5/21 14:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8433586241934296391L;

    private String name;
    private Integer age;

}
