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
 * 积分变化历史记录表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
@Data
@TableName("ums_integration_change_history")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "积分变化历史记录表")
public class IntegrationChangeHistory extends Model<IntegrationChangeHistory> {
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
     * 改变类型：0->增加；1->减少
     */
    @ApiModelProperty(value="改变类型：0->增加；1->减少")
    private Integer changeType;
    /**
     * 积分改变数量
     */
    @ApiModelProperty(value="积分改变数量")
    private Integer changeCount;
    /**
     * 操作人员
     */
    @ApiModelProperty(value="操作人员")
    private String operateMan;
    /**
     * 操作备注
     */
    @ApiModelProperty(value="操作备注")
    private String operateNote;
    /**
     * 积分来源：0->购物；1->管理员修改
     */
    @ApiModelProperty(value="积分来源：0->购物；1->管理员修改")
    private Integer sourceType;
    }
