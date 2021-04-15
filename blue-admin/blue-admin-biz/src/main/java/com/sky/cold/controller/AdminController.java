package com.sky.cold.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.cold.common.rest.controller.SuperController;
import com.sky.cold.common.rest.responses.SuccessResponses;
import com.sky.cold.entity.Admin;
import com.sky.cold.security.util.SecurityUtil;
import com.sky.cold.service.AdminService;
import com.sky.cold.vo.AdminPasswordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
     * 通过用户名称获取用户信息
     */
    @ApiOperation(value = "通过用户名称获取用户信息")
    @GetMapping("/getAdminUserInfoByUserName/{userName}")
    public SuccessResponses<Admin> getAdminUserInfoByUserName(@PathVariable("userName") String userName){
        return success(adminService.getAdminUserInfoByUserName(userName));
    }

    /**
     * 通过Authorization获取用户信息
     */
    @ApiOperation(value = "通过Authorization获取用户信息")
    @GetMapping("/getAdminUserInfo")
    public SuccessResponses<Admin> getAdminUserInfo(){
        return success(adminService.getAdminUserInfoByUserName(securityUtil.getUser()));
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
        return success(adminService.logout(securityUtil.getUser()));
    }

    /**
     * 列表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping(value = {"/getAdminInfoList/{pageNum}/{pageSize}/{keyword}","/getAdminInfoList/{pageNum}/{pageSize}"})
    public SuccessResponses<IPage<Admin>> getAdminInfoList(@PathVariable(value = "keyword", required = false) String keyword,
                                                           @PathVariable(value = "pageSize") Integer pageSize,
                                                           @PathVariable(value = "pageNum") Integer pageNum){
        return success(adminService.getAdminInfoList(keyword,pageNum,pageSize));
    }

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "通过id获取该用户信息")
    @GetMapping("/getUserInfo/{id}")
    public SuccessResponses<Admin> getUserInfo(@NotNull(message = "id不能为空") @PathVariable("id") Long id){
        return success(adminService.getUserInfo(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改后台用户表")
    @PutMapping("/updateAdminInfo")
    public SuccessResponses<Boolean> updateAdminInfo(@RequestBody Admin admin){
        return success(adminService.updateAdminInfo(admin));
    }

    /**
     * 批量修改用户状态
     */
    @ApiOperation(value = "批量修改用户状态")
    @DeleteMapping("/deleteAdminInfo")
    public SuccessResponses<Boolean> deleteAdminInfo(String ids){
        return success(adminService.deleteAdminInfo(ids));
    }

    /**
     * 修改密码
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    public SuccessResponses<Boolean> updatePassword(@RequestBody @Validated AdminPasswordVo adminPasswordVo){
        return success(adminService.updatePassword(adminPasswordVo,securityUtil.getUser()));
    }

    /**
     * 给用户分配角色
     */
    @ApiOperation(value = "给用户分配角色")
    @PostMapping("/distributionAdminRoleRelated")
    public SuccessResponses<Boolean> distributionAdminRoleRelated(String roleIds,Long adminId){
        return success(adminService.distributionAdminRoleRelated(roleIds,adminId));
    }

}
