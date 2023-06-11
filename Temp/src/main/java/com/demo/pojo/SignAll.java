package com.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SignAll implements Serializable {
    private Integer total;

    private String userName;

    private static final long serialVersionUID = 1L;
}