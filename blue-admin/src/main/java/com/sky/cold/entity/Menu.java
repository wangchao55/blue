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

package com.sky.cold.admin.entity;

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
 * 后台菜单表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_menu")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台菜单表")
public class Menu extends Model<Menu> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 父级ID
     */
    @ApiModelProperty(value="父级ID")
    private Long parentId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value="菜单名称")
    private String title;
    /**
     * 菜单级数
     */
    @ApiModelProperty(value="菜单级数")
    private Integer level;
    /**
     * 菜单排序
     */
    @ApiModelProperty(value="菜单排序")
    private Integer sort;
    /**
     * 前端名称
     */
    @ApiModelProperty(value="前端名称")
    private String name;
    /**
     * 前端图标
     */
    @ApiModelProperty(value="前端图标")
    private String icon;
    /**
     * 前端隐藏
     */
    @ApiModelProperty(value="前端隐藏")
    private Integer hidden;
    }
