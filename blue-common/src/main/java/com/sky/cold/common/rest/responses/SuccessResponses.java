package com.sky.cold.common.rest.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 成功返回
 *
 * @author wangchao
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="成功返回", description="调用接口成功后返回的数据")
public class SuccessResponses<T>{

    private static final long serialVersionUID = 1L;
    /**
     * http 状态码
     */
    @ApiModelProperty(value = "HTTP状态码")
    private Integer status;
    /**
     * 结果集返回
     */
    @ApiModelProperty(value = "结果集",position = 1)
    private T result;



}
