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
 * 后台用户权限表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_permission")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户权限表")
public class Permission extends Model<Permission> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 父级权限id
     */
    @ApiModelProperty(value="父级权限id")
    private Long pid;
    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 权限值
     */
    @ApiModelProperty(value="权限值")
    private String value;
    /**
     * 图标
     */
    @ApiModelProperty(value="图标")
    private String icon;
    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @ApiModelProperty(value="权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;
    /**
     * 前端资源路径
     */
    @ApiModelProperty(value="前端资源路径")
    private String uri;
    /**
     * 启用状态；0->禁用；1->启用
     */
    @ApiModelProperty(value="启用状态；0->禁用；1->启用")
    private Integer status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer sort;
    }
