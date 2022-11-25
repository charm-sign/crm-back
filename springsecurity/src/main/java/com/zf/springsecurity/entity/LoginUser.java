package com.zf.springsecurity.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.font.ShapeGraphicAttribute;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
* @ClassName: UserDetailsServiceImpl
* @Description: 明细
* @Author: ZF
* @date: 2022/11/20 14:26
*/
@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginUser implements UserDetails {

        private Employee employee;
private List<String> permissions;
public LoginUser(Employee employee,List<String> permissions){
    this.employee=employee;
    this.permissions=permissions;
}
@JSONField(serialize = false)
private List<SimpleGrantedAuthority> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if (authorities!=null){
                return authorities;
            }
            authorities=permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return authorities;
        }

        @Override
        public String getPassword() {
            return employee.getPassword();
        }

        @Override
        public String getUsername() {
            return employee.getName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }