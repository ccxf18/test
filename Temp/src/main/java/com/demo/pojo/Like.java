package com.demo.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * like
 * @author 
 */
@Data
public class Like implements Serializable {
    private Integer id;

    private String userName;

    private Integer chatId;

    private static final long serialVersionUID = 1L;
}