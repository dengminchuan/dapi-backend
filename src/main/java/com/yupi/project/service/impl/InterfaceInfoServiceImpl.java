package com.yupi.project.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.BaseSearch;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.InterfaceInfoMapper;
import com.yupi.project.mapper.UserInterfaceMapper;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.UserInterface;
import com.yupi.project.service.InterfaceInfoService;
import com.yupi.project.service.UserInterfaceService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author lv jiang er hao
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-01-26 15:39:40
*/
@Service
@Slf4j
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Resource
    private UserInterfaceService userInterfaceService;
    @Resource
    private UserInterfaceMapper userInterfaceMapper;

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name) || ObjectUtils.anyNull(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    @Override
    public String getWithPathParameters(String url,String signNonce,Long userId) {
        //访问网址
        try (HttpResponse httpResponse = HttpRequest.get(url).header("signNonce",signNonce).
                header("userId", String.valueOf(userId)).execute()) {
            String body = httpResponse.body();
            updateInterfaceCount(userId,httpResponse);
            return body;
        }
    }

    /**
     * 访问成功后次数-1
     * @param userId 调用者
     * @param httpResponse 接口返回信息
     */
    private void updateInterfaceCount(Long userId,HttpResponse httpResponse) {
        if(httpResponse.getStatus()==200){
            userInterfaceMapper.subInterfaceLeftCountById(userId,1);
        }
    }

    @Override
    public String getWithJsonParameters(String url, String requestBody,String signNonce,Long userId) {
        try (HttpResponse httpResponse = HttpRequest.get(url).header("signNonce",signNonce).
                header("userId", String.valueOf(userId)).body(requestBody).execute()) {
            String body = httpResponse.body();
            updateInterfaceCount(userId,httpResponse);
            return body;
        }
    }

    @Override
    public String postWithJson(String url, String requestBody,String signNonce,Long userId) {
        try (HttpResponse httpResponse = HttpRequest.post(url).header("signNonce",signNonce).
                header("userId", String.valueOf(userId)).body(requestBody).execute()) {
            String body = httpResponse.body();
            updateInterfaceCount(userId,httpResponse);
            return body;
        }
    }

    @Override
    public Page<InterfaceInfo> searchByCondition(BaseSearch baseSearch) {
        //模糊查询
        String name = baseSearch.getName();
        String description = baseSearch.getDescription();
        Long userId = baseSearch.getUserId();
        String url = baseSearch.getUrl();
        Integer current= baseSearch.getCurrent();
        Integer pageSize= baseSearch.getPageSize();
        if(pageSize==null){
            pageSize=5;
        }
        QueryWrapper<InterfaceInfo> interfaceInfoQueryWrapper = new QueryWrapper<>();
        Page<InterfaceInfo> interfaceInfoPage = new Page<>(current,pageSize);
        interfaceInfoQueryWrapper.like(StringUtils.isNotBlank(name),"name",name)
                .like(StringUtils.isNotBlank(description),"description",description)
                .like(StringUtils.isNotBlank(url),"url",url);
        if(userId!=null){
            interfaceInfoQueryWrapper.eq("userId",userId);
        }
        Page<InterfaceInfo> page = page(interfaceInfoPage, interfaceInfoQueryWrapper);
        return page;
    }
}




