package com.EmployeeManagement11.employeeManagement.service.serviceImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.repo.EmployeeQuery;
import com.EmployeeManagement11.employeeManagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeQuery employeeQuery;

    public EmployeeServiceImpl(EmployeeQuery employeeQuery) {
        this.employeeQuery = employeeQuery;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeQuery.saveData(employee);

    }

    @Override
    public void deleteEmployee(int id) {
        employeeQuery.deleteData(id);

    }

    @Override
    public Employee getEmployee(int id) {
        return employeeQuery.getData(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeQuery.updateData(employee);
    }

//    @Override
//    public List<Employee> getAllEmployee() {
//        return employeeQuery.getALlData();
//    }
}
