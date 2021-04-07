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
 * 后台用户角色表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户角色表")
public class Role extends Model<Role> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;
    /**
     * 后台用户数量
     */
    @ApiModelProperty(value="后台用户数量")
    private Integer adminCount;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    /**
     * 启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value="启用状态：0->禁用；1->启用")
    private Integer status;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer sort;
    }
