package com.EmployeeManagement11.employeeManagement.enitiy;

import com.EmployeeManagement11.employeeManagement.repo.SingUpQuery;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Roles implements GrantedAuthority {
    ADMIN,USER;

    @Override
    public String getAuthority() {
        return name();
    }
    public static List<Roles> getRolesFromDatabase(String username, SingUpQuery userRepository) {
        SingUp user = userRepository.findUserByName(username);
        return user.getRoles();
    }
}
