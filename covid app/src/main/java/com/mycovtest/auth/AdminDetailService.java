package com.mycovtest.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        switch (userName) {
            case "admin":
                return new AdminUserDetails("admin", "12345");
            case "tester":
                return new AdminUserDetails("tester", "abcde");
            default:
                throw new UsernameNotFoundException("Invalid user name");
        }
    }
}
