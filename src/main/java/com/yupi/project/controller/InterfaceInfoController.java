package com.yupi.project.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.project.common.*;
import com.yupi.project.constant.CommonConstant;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.yupi.project.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.yupi.project.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.yupi.project.model.dto.interfaceInfo.InterfaceInvokeRequest;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.entity.User;
import com.yupi.project.model.entity.Userkey;
import com.yupi.project.service.InterfaceInfoService;
import com.yupi.project.service.UserService;
import com.yupi.project.service.UserkeyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@RestController
@RequestMapping("/InterfaceInfo")
@Slf4j(topic = "interfaceInfo")
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService InterfaceInfoService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;
    @Resource
    private UserkeyService userkeyService;


    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        interfaceInfo.setUpdateTime(new Date());
        // 校验
        InterfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        boolean result = InterfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer userId = deleteRequest.getUserId();
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = InterfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅管理员可删除
        User user = userService.getById(userId);
        if(user==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (!user.getUserRole().equals("admin")) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = InterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
                                            HttpServletRequest request) {

        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        InterfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        interfaceInfo.setUpdateTime(new Date());
        // 仅本人或管理员可修改
        Long userId = Long.valueOf(request.getHeader("updateId"));
        User user = userService.getById(userId);
        if(user.getUserRole()=="admin"||interfaceInfoUpdateRequest.getUserId()==userId){
            boolean result = InterfaceInfoService.updateById(interfaceInfo);
            return ResultUtils.success(result);
        }
       return ResultUtils.error(ErrorCode.NO_AUTH_ERROR);

    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfo = InterfaceInfoService.getById(id);
        return ResultUtils.success(InterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param InterfaceInfoQueryRequest
     * @return
     */
//    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest InterfaceInfoQueryRequest) {
        InterfaceInfo InterfaceInfoQuery = new InterfaceInfo();
        if (InterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(InterfaceInfoQueryRequest, InterfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(InterfaceInfoQuery);
        List<InterfaceInfo> InterfaceInfoList = InterfaceInfoService.list(queryWrapper);
        return ResultUtils.success(InterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param InterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest InterfaceInfoQueryRequest, HttpServletRequest request) {
        if (InterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo InterfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(InterfaceInfoQueryRequest, InterfaceInfoQuery);
        long current = InterfaceInfoQueryRequest.getCurrent();
        long size = InterfaceInfoQueryRequest.getPageSize();
        String sortField = InterfaceInfoQueryRequest.getSortField();
        String sortOrder = InterfaceInfoQueryRequest.getSortOrder();
        String content = InterfaceInfoQuery.getDescription();
        // content 需支持模糊搜索
        InterfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(InterfaceInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfo> InterfaceInfoPage = InterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(InterfaceInfoPage);
    }
    @PostMapping("/invoke")
    public BaseResponse<String> invokeInterface(@RequestBody InterfaceInvokeRequest interfaceInvokeRequest, HttpServletRequest request){
        //访问后端接口,ak sk 签名认证

        Long interfaceId = interfaceInvokeRequest.getId();
        String url = interfaceInvokeRequest.getUrl();
        String requestBody = interfaceInvokeRequest.getRequestBody();
        String responseBody = interfaceInvokeRequest.getResponseBody();
        log.info("请求url:{},请求的接口id:{},请求体为:{}",url,interfaceId,requestBody);
        //签名认证
        String accessKey = request.getHeader("accessKey");
        String sign = request.getHeader("sign");
        Integer nonce = Integer.valueOf(request.getHeader("nonce"));
        if(StringUtils.isAnyBlank(sign,accessKey)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        log.info("sign:{},accessKey:{}",sign,accessKey);
        Userkey userKey = userkeyService.getOne(new QueryWrapper<Userkey>().eq("accessKey", accessKey));
        if(userKey==null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        boolean signRight = SignUtil.checkSign(sign,nonce,userKey.getAccessKey(), String.valueOf(userKey.getUserId()), userKey.getSecretKey());
        if(!signRight){
            //签名校验失败
            log.info("签名校验失败");
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }


        if(StringUtils.isBlank(url)||interfaceId==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //如果网址不一样且请求方式为get说明为{param}方式，则直接访问，失败则报错。
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(interfaceId);
        //接口是关闭状态
        if(interfaceInfo.getStatus()==0){
            return ResultUtils.error(ErrorCode.FORBIDDEN_ERROR);
        }
        if(!StringUtils.equals(url,interfaceInfo.getUrl())&&StringUtils.equalsIgnoreCase("GET",interfaceInfo.getMethod())){
            log.info("执行get的路径参数请求");
            String result = interfaceInfoService.getWithPathParameters(url);
            return ResultUtils.success(result);
        }
        //其他情况
        //1.get请求但参数为JSON格式
        if(StringUtils.equalsIgnoreCase("GET",interfaceInfo.getMethod())&& JSONUtil.isTypeJSON(requestBody)){
            log.info("执行post参数get请求");
            String result=interfaceInfoService.getWithJsonParameters(url,requestBody);
            return ResultUtils.success(result);
        }
        //2.post请求
        if(StringUtils.equalsIgnoreCase("POST",interfaceInfo.getMethod())){
            log.info("执行POST请求");
            String result=interfaceInfoService.postWithJson(url,requestBody);
            return ResultUtils.success(result);
        }


        return ResultUtils.error(200,"参数错误");
    }
    @GetMapping("/search")
    public BaseResponse<Page<InterfaceInfo>> searchByCondition(BaseSearch baseSearch,HttpServletRequest request){
        Page<InterfaceInfo> pageInfo=interfaceInfoService.searchByCondition(baseSearch);
        //数据库实现搜索功能
        return ResultUtils.success(pageInfo);
    }
    @GetMapping("/nonce")
    public BaseResponse<Integer> getNonce(){
        return ResultUtils.success(SignUtil.getNonce());
    }

}
