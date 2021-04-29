package com.sky.cold.common.enums;

/**
 * @author solang
 * @date 2021-04-25 9:28 上午
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
