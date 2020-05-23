package com.oxygen.utils.bussines.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class DataDTO implements Serializable {

    private String x;

    private String y;

    private String z;

    public DataDTO(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
