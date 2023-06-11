package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * income
 * @author 
 */
@Data
public class Income implements Serializable {
    private Integer id;

    private String userName;

    private Date time;

    private String name;

    private String way;

    private Integer sum;

    private String remark;

    private static final long serialVersionUID = 1L;
}