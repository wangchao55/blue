package com.sky.cold.common.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wangchao
 * @date 2021-05-29 10:49 上午
 */
@Data
public class UserInfoDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;
}
