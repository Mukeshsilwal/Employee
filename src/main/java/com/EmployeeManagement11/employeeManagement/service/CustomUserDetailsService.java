package com.EmployeeManagement11.employeeManagement.service;

import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;
import com.EmployeeManagement11.employeeManagement.repo.SignUpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SignUpQuery employeeQuery;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SignUp singUp =employeeQuery.findUserByName(username);
        return User.withUsername(singUp.getName()).password(singUp.getPassword())
                .disabled(singUp.isEnabled()).authorities(singUp.getAuthorities()).build();
    }
}
