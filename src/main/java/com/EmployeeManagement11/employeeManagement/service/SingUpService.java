package com.EmployeeManagement11.employeeManagement.service;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;

public interface SingUpService {
    SingUp createSingUp(SingUp singUp, Roles roles);
}
