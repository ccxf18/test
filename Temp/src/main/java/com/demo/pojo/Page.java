package com.demo.pojo;

import lombok.Data;

@Data
public class Page {
    public int pagesize;
    public int page;
    public String userName;
    private static final long serialVersionUID = 1L;
}
