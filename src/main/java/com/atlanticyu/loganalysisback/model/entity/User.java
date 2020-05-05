package com.atlanticyu.loganalysisback.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//此处所创建的 User类继承了 Spring Security的 UserDetails接口，
// 从而成为了一个符合 Security安全的用户，即通过继承 UserDetails，
// 即可实现 Security中相关的安全功能。
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    //CascadeType.REFRESH：级联刷新，当多个用户同时作操作一个实体，
    // 为了用户取到的数据是实时的，在用实体中的数据之前就可以调用
    // 一下refresh()方法，同时借助此类注解可实现多表级联CRUD
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //为实现UserDetails而需要的重写方法！
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(roles != null)
        for (Role role : roles) {
            authorities.add( new SimpleGrantedAuthority( role.getRolename()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
