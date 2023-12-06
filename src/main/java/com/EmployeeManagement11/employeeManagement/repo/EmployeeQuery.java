package com.EmployeeManagement11.employeeManagement.repo;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeQuery {
    void saveData(Employee employee);
    void deleteData(int id);
    Employee getData(int id);
    Employee updateData(Employee employee);
}
