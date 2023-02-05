package com.yupi.project.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName userkey
 */
@TableName(value ="userkey")
@Data
public class Userkey implements Serializable {
    public Userkey(Long userId, String accessKey, String secretKey) {
        this.userId = userId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 
     */
    @TableId
    private Long userId;

    /**
     * 
     */
    private String accessKey;

    /**
     * 
     */
    private String secretKey;

    /**
     * 逻辑删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}