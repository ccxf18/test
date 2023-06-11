package com.demo.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * way_list
 * @author 
 */
@Data
public class WayList implements Serializable {
    private Integer id;

    private String way;

    private static final long serialVersionUID = 1L;
}