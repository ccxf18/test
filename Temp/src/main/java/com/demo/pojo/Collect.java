package com.demo.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * collect
 * @author 
 */
@Data
public class Collect implements Serializable {
    private Integer id;

    private String userName;

    private Integer chatId;

    private static final long serialVersionUID = 1L;
}