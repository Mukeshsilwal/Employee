package com.EmployeeManagement11.employeeManagement.service;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import com.EmployeeManagement11.employeeManagement.repo.EmployeeQuery;
import com.EmployeeManagement11.employeeManagement.repo.SingUpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SingUpQuery employeeQuery;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SingUp singUp =employeeQuery.findUserByName(username);
        return User.withUsername(singUp.getName()).password(singUp.getPassword())
                .disabled(singUp.isEnabled()).authorities(singUp.getAuthorities()).build();
    }
}
