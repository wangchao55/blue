package com.sky.cold.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 获取RSA公钥接口
 * Created by wangchao on 2020/6/19.
 */
@RestController
@Api(tags = "RSA加密")
@RequestMapping("/rsa")
public class KeyPairController extends SuperController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    @ApiOperation(value = "获取RSA公钥接口")
    public SuccessResponses<Map<String, Object>> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return success(new JWKSet(key).toJSONObject());
    }

}
