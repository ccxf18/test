package com.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Count implements Serializable {
    private Integer total;

    private String day;

    private static final long serialVersionUID = 1L;
}