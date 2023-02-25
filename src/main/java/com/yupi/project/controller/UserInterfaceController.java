//-*- coding =utf-8 -*-
//@Time : 2023/2/25
//@Author: 邓闽川
//@File  UserInterfaceController.java
//@software:IntelliJ IDEA
package com.yupi.project.controller;

import com.yupi.project.common.BaseResponse;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.common.ResultUtils;
import com.yupi.project.model.dto.userInterface.UserInterfaceUpdateRequest;
import com.yupi.project.service.UserInterfaceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/userInterface")
public class UserInterfaceController {
    @Resource
    private UserInterfaceService userInterfaceService;
    @PostMapping("/updateInterfaceLeftCountById")
    public BaseResponse<Integer> subInterfaceLeftCountById(@RequestBody UserInterfaceUpdateRequest userInterfaceUpdateRequest){
        Long userId = userInterfaceUpdateRequest.getUserId();
        Integer changeNumber = userInterfaceUpdateRequest.getChangeNumber();
        Boolean isAdd = userInterfaceUpdateRequest.getIsAdd();
        if(userId==null||changeNumber==null||isAdd==null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        Integer leftCount = userInterfaceService.updateInterfaceLeftCountById(userId, isAdd,changeNumber);
        return ResultUtils.success(leftCount);
    }
    @GetMapping("/checkLeftCountById")
    public BaseResponse<Integer> checkLeftCountById(@RequestParam Long userId){
        Integer leftCount = userInterfaceService.getLeftCount(userId);
        if(leftCount==null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(leftCount);
    }

}
