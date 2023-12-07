package com.EmployeeManagement11.employeeManagement.repo;


import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;

public interface SignUpQuery {

void createSignUp(SignUp singUp, Roles roles);
SignUp findUserByName(String name);
}
