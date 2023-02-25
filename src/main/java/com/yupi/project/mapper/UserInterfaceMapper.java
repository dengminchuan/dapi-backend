package com.yupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.project.model.entity.UserInterface;
import org.springframework.web.bind.annotation.RequestParam;


/**
* @author lv jiang er hao
* @description 针对表【user_interface(用户调用接口表)】的数据库操作Mapper
* @createDate 2023-02-11 21:13:21
* @Entity generator.domain.UserInterface
*/
public interface UserInterfaceMapper extends BaseMapper<UserInterface> {
    void subInterfaceLeftCountById(Long userId,@RequestParam("changeNumber") Integer changeNumber);
    void addInterfaceLeftCountById(Long userId,@RequestParam("changeNumber") Integer changeNumber);
    Integer getLeftCountById(Long userId);

}




