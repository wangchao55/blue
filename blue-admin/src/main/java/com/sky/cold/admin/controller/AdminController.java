package com.sky.cold.admin.controller;

import com.sky.cold.admin.entity.Admin;
import com.sky.cold.admin.service.AdminService;
import com.sky.cold.rest.controller.SuperController;
import com.sky.cold.rest.responses.SuccessResponses;
import com.sky.cold.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


/**
 * 后台用户表
 *
 * @author wangchao
 * @date 2021-03-30 16:54:44
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "后台用户管理")
public class AdminController extends SuperController {

    @Autowired
    private AdminService adminService;

    @Autowired
    SecurityUtil securityUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public SuccessResponses list(@RequestParam Map<String, Object> params){
        Object page = adminService.list();

        return success(page);
    }


    /**
     * 通过用户名称获取用户信息
     */
    @ApiOperation(value = "通过用户名称获取用户信息")
    @GetMapping("/getAdminUserInfoByUserName/{userName}")
    public SuccessResponses<Admin> getAdminUserInfoByUserName(@PathVariable("userName") String userName){
        return success(adminService.getAdminUserInfoByUserName(userName));
    }

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getAdminUserInfo")
    public SuccessResponses<Admin> getAdminUserInfo(){
        return success(adminService.getAdminUserInfoByUserName(securityUtil.getUser().getUsername()));
    }

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public SuccessResponses<Boolean> register(@Validated @RequestBody Admin admin){
        return success(adminService.register(admin));
    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public SuccessResponses<Map<String,Object>> login(@Validated @RequestBody Admin admin){
        return success(adminService.login(admin));
    }

    /**
     * 刷新token
     */
    @ApiOperation(value = "刷新token")
    @GetMapping("/refreshToken")
    public SuccessResponses<Map<String,Object>> refreshToken(HttpServletRequest request){
        return success(adminService.refreshToken(request.getHeader(tokenHeader)));
    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public SuccessResponses<Void> logout(){
        return success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户表")
    @PutMapping("/update")
    public SuccessResponses update(@RequestBody Admin admin){
		adminService.updateById(admin);

        return success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "通过id删除后台用户表")
    @DeleteMapping("/delete")
    public SuccessResponses delete(@RequestBody Long[] ids){
		adminService.removeByIds(Arrays.asList(ids));

        return success();
    }

}
