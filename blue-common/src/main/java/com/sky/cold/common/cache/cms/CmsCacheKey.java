package com.sky.cold.common.cache.cms;

/**
 * @Author: wangchao
 * @Date: 2021/4/8 15:17
 */
public class CmsCacheKey {

    public static final String cms = "cms";

    /**
     * 后台token
     */
    public static final String adminToken = cms + ":token:" + "{0}";

    /**
     * 后台用户信息
     */
    public static final String adminInfo = cms + ":admin:" + "{0}";
}
