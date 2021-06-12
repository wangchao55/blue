package com.sky.cold.common.enums;

/**
 * @author wangchao
 * @date 2021-05-29 10:40 上午
 */
public enum CommonTypeEnum {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private Integer code;

    private CommonTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


}
