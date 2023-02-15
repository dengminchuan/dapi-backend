package com.yupi.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.mapper.UserInterfaceMapper;
import com.yupi.project.model.entity.UserInterface;
import com.yupi.project.service.UserInterfaceService;
import org.springframework.stereotype.Service;

/**
* @author lv jiang er hao
* @description 针对表【user_interface(用户调用接口表)】的数据库操作Service实现
* @createDate 2023-02-11 21:13:21
*/
@Service
public class UserInterfaceServiceImpl extends ServiceImpl<UserInterfaceMapper, UserInterface>
        implements UserInterfaceService {

}




