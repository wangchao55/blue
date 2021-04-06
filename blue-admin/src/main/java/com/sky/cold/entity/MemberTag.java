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
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 用户标签表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_tag")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户标签表")
public class MemberTag extends Model<MemberTag> {
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
    private String name;
    /**
     * 自动打标签完成订单数量
     */
    @ApiModelProperty(value="自动打标签完成订单数量")
    private Integer finishOrderCount;
    /**
     * 自动打标签完成订单金额
     */
    @ApiModelProperty(value="自动打标签完成订单金额")
    private BigDecimal finishOrderAmount;
    }
