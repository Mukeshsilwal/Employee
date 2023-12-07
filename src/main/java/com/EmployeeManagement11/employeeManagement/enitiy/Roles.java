package com.EmployeeManagement11.employeeManagement.enitiy;

import com.EmployeeManagement11.employeeManagement.repo.SignUpQuery;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public enum Roles implements GrantedAuthority {
    ADMIN,USER;

    @Override
    public String getAuthority() {
        return name();
    }
    public static List<Roles> getRolesFromDatabase(String username, SignUpQuery userRepository) {
        SignUp user = userRepository.findUserByName(username);
        return user.getRoles();
    }
}
