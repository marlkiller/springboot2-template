package com.bespin.wzu3.config.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author voidm
 * @date 2021/2/19
 */
@Data
public class JwtUser implements UserDetails {


    public JwtUser (String username) {
        this.username = username;
    }

    public JwtUser (String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;


    public String getUsername () {
        return username;
    }

    public String getPassword () {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}