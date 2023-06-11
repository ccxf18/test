package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * chat
 * @author 
 */
@Data
public class Chat implements Serializable {
    private Integer id;

    private String content;

    private Date time;

    private Integer collect;

    private Integer like;

    private String author;

    private Boolean likeStatus;

    private Boolean collectStatus;

    private static final long serialVersionUID = 1L;
}