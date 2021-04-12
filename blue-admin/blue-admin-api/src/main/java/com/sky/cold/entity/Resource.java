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
 * 后台资源表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_resource")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台资源表")
public class Resource extends Model<Resource> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    /**
     * 资源名称
     */
    @ApiModelProperty(value="资源名称")
    private String name;
    /**
     * 资源URL
     */
    @ApiModelProperty(value="资源URL")
    private String url;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;
    /**
     * 资源分类ID
     */
    @ApiModelProperty(value="资源分类ID")
    private Long categoryId;
    }
