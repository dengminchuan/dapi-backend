package com.yupi.project.common;

import com.yupi.project.model.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author yupi
 */
@Data
public class DeleteRequest implements Serializable {
    /**
     * id
     */

    private Integer userId;
    private Long id;

    private static final long serialVersionUID = 1L;
}