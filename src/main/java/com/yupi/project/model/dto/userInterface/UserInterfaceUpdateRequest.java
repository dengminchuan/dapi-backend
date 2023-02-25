//-*- coding =utf-8 -*-
//@Time : 2023/2/25
//@Author: 邓闽川
//@File  UserInterfaceUpdate.java
//@software:IntelliJ IDEA
package com.yupi.project.model.dto.userInterface;

import lombok.Data;

@Data
public class UserInterfaceUpdateRequest {
    private Long userId;
    private Boolean isAdd;
    private Integer changeNumber;
}
