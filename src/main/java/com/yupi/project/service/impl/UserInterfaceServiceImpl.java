package com.yupi.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.mapper.UserInterfaceMapper;
import com.yupi.project.model.entity.UserInterface;
import com.yupi.project.service.UserInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author lv jiang er hao
* @description 针对表【user_interface(用户调用接口表)】的数据库操作Service实现
* @createDate 2023-02-11 21:13:21
*/
@Service
public class UserInterfaceServiceImpl extends ServiceImpl<UserInterfaceMapper, UserInterface>
        implements UserInterfaceService {
    @Resource
    private UserInterfaceMapper userInterfaceMapper;
    /**
     * 增加或减少指定user的接口调用次数
     * @param userId 指定更新的userId
     * @param isAdd 是否为增加操作
     * @return 返回剩余调用次数
     */
    @Override
    public Integer updateInterfaceLeftCountById(Long userId, Boolean isAdd,Integer changeNumber) {
        if(isAdd){
            userInterfaceMapper.addInterfaceLeftCountById(userId,changeNumber);
        }else{
            userInterfaceMapper.subInterfaceLeftCountById(userId,changeNumber);
        }
        return userInterfaceMapper.getLeftCountById(userId);
    }

    @Override
    public Integer getLeftCount(Long userId) {
        return userInterfaceMapper.getLeftCountById(userId);
    }
}




