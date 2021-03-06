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
import java.io.Serializable;

/**
 * 积分消费设置
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_integration_consume_setting")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "积分消费设置")
public class IntegrationConsumeSetting extends Model<IntegrationConsumeSetting> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Long id;
    /**
     * 每一元需要抵扣的积分数量
     */
    @ApiModelProperty(value="每一元需要抵扣的积分数量")
    private Integer deductionPerAmount;
    /**
     * 每笔订单最高抵用百分比
     */
    @ApiModelProperty(value="每笔订单最高抵用百分比")
    private Integer maxPercentPerOrder;
    /**
     * 每次使用积分最小单位100
     */
    @ApiModelProperty(value="每次使用积分最小单位100")
    private Integer useUnit;
    /**
     * 是否可以和优惠券同用；0->不可以；1->可以
     */
    @ApiModelProperty(value="是否可以和优惠券同用；0->不可以；1->可以")
    private Integer couponStatus;
    }
