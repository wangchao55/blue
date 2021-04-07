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
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 会员统计信息
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_member_statistics_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "会员统计信息")
public class MemberStatisticsInfo extends Model<MemberStatisticsInfo> {
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
     * 累计消费金额
     */
    @ApiModelProperty(value="累计消费金额")
    private BigDecimal consumeAmount;
    /**
     * 订单数量
     */
    @ApiModelProperty(value="订单数量")
    private Integer orderCount;
    /**
     * 优惠券数量
     */
    @ApiModelProperty(value="优惠券数量")
    private Integer couponCount;
    /**
     * 评价数
     */
    @ApiModelProperty(value="评价数")
    private Integer commentCount;
    /**
     * 退货数量
     */
    @ApiModelProperty(value="退货数量")
    private Integer returnOrderCount;
    /**
     * 登录次数
     */
    @ApiModelProperty(value="登录次数")
    private Integer loginCount;
    /**
     * 关注数量
     */
    @ApiModelProperty(value="关注数量")
    private Integer attendCount;
    /**
     * 粉丝数量
     */
    @ApiModelProperty(value="粉丝数量")
    private Integer fansCount;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer collectProductCount;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer collectSubjectCount;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer collectTopicCount;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer collectCommentCount;
    /**
     * 
     */
    @ApiModelProperty(value="")
    private Integer inviteFriendCount;
    /**
     * 最后一次下订单时间
     */
    @ApiModelProperty(value="最后一次下订单时间")
    private Date recentOrderTime;
    }
