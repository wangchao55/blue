/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.sky.cold.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.io.Serializable;

/**
 * 后台用户表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_admin")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户表")
public class Admin extends Model<Admin> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String username;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String password;
    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String icon;
    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;
    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickName;
    /**
     * 备注信息
     */
    @ApiModelProperty(value="备注信息")
    private String note;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value="最后登录时间")
    private Date loginTime;
    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value="帐号启用状态：0->禁用；1->启用")
    private Integer status;
    }
