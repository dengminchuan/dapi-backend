package com.yupi.project.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
public class UserInterfaceMapperTest {
    @Resource
    private UserInterfaceMapper userInterfaceMapper;

    @Test
    public void subInterfaceLeftCountById() {
        userInterfaceMapper.subInterfaceLeftCountById(1L,10);
    }
}