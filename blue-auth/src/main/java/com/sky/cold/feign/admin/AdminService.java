package com.sky.cold.feign.admin;

import com.sky.cold.common.constant.FeignConstant;
import com.sky.cold.common.entity.dto.UserInfoDto;
import com.sky.cold.common.rest.responses.SuccessResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangchao
 * @date 2021-05-29 1:41 下午
 */
@FeignClient(FeignConstant.BLUE_ADMIN)
public interface AdminService {

    @GetMapping("/admin/loadUserByUsername")
    SuccessResponses<UserInfoDto> loadUserByUsername(@RequestParam String userName);
}
