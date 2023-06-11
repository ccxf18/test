package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sign
 * @author 
 */
@Data
public class Sign implements Serializable {
    private Integer id;

    private String userName;

    private Date time;

    private static final long serialVersionUID = 1L;
}