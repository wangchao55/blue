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
 * 会员等级表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_level")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "会员等级表")
public class MemberLevel extends Model<MemberLevel> {
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
     * 
     */
    @ApiModelProperty(value="")
    private Integer growthPoint;
    /**
     * 是否为默认等级：0->不是；1->是
     */
    @ApiModelProperty(value="是否为默认等级：0->不是；1->是")
    private Integer defaultStatus;
    /**
     * 免运费标准
     */
    @ApiModelProperty(value="免运费标准")
    private BigDecimal freeFreightPoint;
    /**
     * 每次评价获取的成长值
     */
    @ApiModelProperty(value="每次评价获取的成长值")
    private Integer commentGrowthPoint;
    /**
     * 是否有免邮特权
     */
    @ApiModelProperty(value="是否有免邮特权")
    private Integer priviledgeFreeFreight;
    /**
     * 是否有签到特权
     */
    @ApiModelProperty(value="是否有签到特权")
    private Integer priviledgeSignIn;
    /**
     * 是否有评论获奖励特权
     */
    @ApiModelProperty(value="是否有评论获奖励特权")
    private Integer priviledgeComment;
    /**
     * 是否有专享活动特权
     */
    @ApiModelProperty(value="是否有专享活动特权")
    private Integer priviledgePromotion;
    /**
     * 是否有会员价格特权
     */
    @ApiModelProperty(value="是否有会员价格特权")
    private Integer priviledgeMemberPrice;
    /**
     * 是否有生日特权
     */
    @ApiModelProperty(value="是否有生日特权")
    private Integer priviledgeBirthday;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String note;
    }
