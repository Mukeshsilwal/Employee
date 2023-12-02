package com.EmployeeManagement11.employeeManagement.service;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    void deleteEmployee(int id);
    Employee getEmployee(int id);
    Employee updateEmployee(Employee employee);
//    List<Employee>  getAllEmployee();

}
