package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.common.BaseSearch;
import com.yupi.project.model.entity.InterfaceInfo;

/**
* @author lv jiang er hao
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2023-01-26 15:39:40
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

   String getWithPathParameters(String url,String signNonce,Long userId);

    String getWithJsonParameters(String url,String requestBody,String signNonce,Long userId);

    String postWithJson(String url, String requestBody,String signNonce,Long userId);

    Page<InterfaceInfo> searchByCondition(BaseSearch baseSearch);
}
