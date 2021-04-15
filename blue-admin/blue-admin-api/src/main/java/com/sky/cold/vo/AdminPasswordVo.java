package com.sky.cold.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: wangchao
 * @Date: 2021/4/12 20:22
 */
@Data
public class AdminPasswordVo {

    @ApiModelProperty(value = "旧密码")
    @NotEmpty(message = "密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotEmpty(message = "密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "确认新密码")
    @NotEmpty(message = "密码不能为空")
    private String confirmNewPassword;
}
