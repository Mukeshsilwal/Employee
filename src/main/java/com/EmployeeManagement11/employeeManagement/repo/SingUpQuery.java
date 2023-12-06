package com.EmployeeManagement11.employeeManagement.repo;


import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import employee.UserRolesOuterClass;

import java.util.Optional;

public interface SingUpQuery {

void createSingUp(SingUp singUp,Roles roles);
SingUp findUserByName(String name);
}
