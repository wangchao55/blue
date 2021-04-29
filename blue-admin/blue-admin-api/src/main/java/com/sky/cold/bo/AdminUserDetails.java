package com.sky.cold.bo;

import com.sky.cold.entity.Admin;
import com.sky.cold.entity.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {
    @Getter
    @Setter
    private Admin admin;

    @Getter
    @Setter
    private List<Resource> resourceList;

    public AdminUserDetails(Admin admin,List<Resource> resourceList) {
        this.admin = admin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        /*return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());*/
        return null;
    }

    /**
     * 用户密码
     */
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    /**
     * 用户名称
     */
    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    /**
     * 账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否禁用
     */
    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }
}
