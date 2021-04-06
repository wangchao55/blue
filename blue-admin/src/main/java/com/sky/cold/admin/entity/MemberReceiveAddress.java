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
import java.io.Serializable;

/**
 * 会员收货地址表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_receive_address")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "会员收货地址表")
public class MemberReceiveAddress extends Model<MemberReceiveAddress> {
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
     * 收货人名称
     */
    @ApiModelProperty(value="收货人名称")
    private String name;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String phoneNumber;
    /**
     * 是否为默认
     */
    @ApiModelProperty(value="是否为默认")
    private Integer defaultStatus;
    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码")
    private String postCode;
    /**
     * 省份/直辖市
     */
    @ApiModelProperty(value="省份/直辖市")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value="城市")
    private String city;
    /**
     * 区
     */
    @ApiModelProperty(value="区")
    private String region;
    /**
     * 详细地址(街道)
     */
    @ApiModelProperty(value="详细地址(街道)")
    private String detailAddress;
    }
