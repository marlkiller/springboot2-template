package com.bespin.wzu3.config.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component(value="CustomUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public JwtUser loadUserByUsername(String name) throws UsernameNotFoundException {
        // User user = authMapper.findByUsername(name);
        // if (user == null) {
        //     throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        // }
        // Role role = authMapper.findRoleByUserId(user.getId());
        // user.setRole(role);
        // System.out.println(new BCryptPasswordEncoder().encode("363636"));

        // FIXME 这里从数据库查用户信息
        return new JwtUser(name,"$2a$10$RdqAuuhGiswN7GVMo1k/4u7CcBEnwolT4xZ28awz3JHRUcPCl7yri");
        // return new JwtUser(name);
    }
}