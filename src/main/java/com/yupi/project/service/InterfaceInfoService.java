package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.Post;

/**
* @author lv jiang er hao
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2023-01-26 15:39:40
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

   String getWithPathParameters(String url);

    String getWithJsonParameters(String url,String requestBody);
}
