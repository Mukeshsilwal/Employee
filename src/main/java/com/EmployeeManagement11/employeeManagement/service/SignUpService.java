package com.EmployeeManagement11.employeeManagement.service;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;

public interface SignUpService {
    void createSignUp(SignUp signUp, Roles roles);
}
