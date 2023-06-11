package com.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CountMonth implements Serializable {
    private Integer value;

    private String name;

    private static final long serialVersionUID = 1L;
}