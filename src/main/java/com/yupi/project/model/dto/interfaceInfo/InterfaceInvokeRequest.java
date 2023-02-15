//-*- coding =utf-8 -*-
//@Time : 2023/2/3
//@Author: 邓闽川
//@File  InterfaceInvoke.java
//@software:IntelliJ IDEA
package com.yupi.project.model.dto.interfaceInfo;

import lombok.Data;

@Data
public class InterfaceInvokeRequest {
    //访问的网址
    private String url;
    //接口id
    private Long id;
    //请求体，用于封装参数
    private String requestBody;
    //响应体
    private String responseBody;
}
