package com.yupi.project.service.impl;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.BaseResponse;
import com.yupi.project.mapper.UserkeyMapper;
import com.yupi.project.model.entity.Userkey;
import com.yupi.project.service.UserkeyService;
import org.springframework.stereotype.Service;

/**
* @author lv jiang er hao
* @description 针对表【userkey】的数据库操作Service实现
* @createDate 2023-02-02 17:28:36
*/
@Service
public class UserkeyServiceImpl extends ServiceImpl<UserkeyMapper, Userkey>
    implements UserkeyService {

    @Override
    public void setKey(long userId) {
        //5位accessKey,8位secretKey
        String accessKey = IdUtil.simpleUUID().substring(0, 5);
        String secretKey = IdUtil.simpleUUID().substring(0, 8);
        save(new Userkey(userId,accessKey,secretKey));
    }

    @Override
    public Userkey getKey(long userId) {
        return  getOne(new QueryWrapper<Userkey>().eq("userId", userId));
    }
}




