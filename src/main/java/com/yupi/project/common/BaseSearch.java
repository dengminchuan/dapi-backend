//-*- coding =utf-8 -*-
//@Time : 2023/2/5
//@Author: 邓闽川
//@File  BaseSearch.java
//@software:IntelliJ IDEA
package com.yupi.project.common;

import lombok.Data;

@Data
public class BaseSearch {

    private String name;

    private Long userId;

    private String description;

    private String url;

    private Integer current;

    private Integer pageSize;

    public BaseSearch(String name, Long userId, String description, String url,Integer current,Integer pageSize) {
        this.name = name;
        this.userId = userId;
        this.description = description;
        this.url = url;
        this.current = current;
        this.pageSize=pageSize;
    }
}
