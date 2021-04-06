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
 * 会员积分成长规则表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_rule_setting")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "会员积分成长规则表")
public class MemberRuleSetting extends Model<MemberRuleSetting> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 连续签到天数
     */
    @ApiModelProperty(value="连续签到天数")
    private Integer continueSignDay;
    /**
     * 连续签到赠送数量
     */
    @ApiModelProperty(value="连续签到赠送数量")
    private Integer continueSignPoint;
    /**
     * 每消费多少元获取1个点
     */
    @ApiModelProperty(value="每消费多少元获取1个点")
    private BigDecimal consumePerPoint;
    /**
     * 最低获取点数的订单金额
     */
    @ApiModelProperty(value="最低获取点数的订单金额")
    private BigDecimal lowOrderAmount;
    /**
     * 每笔订单最高获取点数
     */
    @ApiModelProperty(value="每笔订单最高获取点数")
    private Integer maxPointPerOrder;
    /**
     * 类型：0->积分规则；1->成长值规则
     */
    @ApiModelProperty(value="类型：0->积分规则；1->成长值规则")
    private Integer type;
    }
