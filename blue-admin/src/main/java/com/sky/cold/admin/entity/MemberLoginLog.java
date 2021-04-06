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
 * 会员登录记录
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_login_log")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "会员登录记录")
public class MemberLoginLog extends Model<MemberLoginLog> {
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
    private Long memberId;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Date createTime;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String ip;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String city;
    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    @ApiModelProperty(value="登录类型：0->PC；1->android;2->ios;3->小程序")
    private Integer loginType;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String province;
    }
