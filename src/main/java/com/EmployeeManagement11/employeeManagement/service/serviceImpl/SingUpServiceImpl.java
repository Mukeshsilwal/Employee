package com.EmployeeManagement11.employeeManagement.service.serviceImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import com.EmployeeManagement11.employeeManagement.repo.SingUpQuery;
import com.EmployeeManagement11.employeeManagement.service.SingUpService;
import org.springframework.stereotype.Service;

@Service
public class SingUpServiceImpl implements SingUpService {
    private final SingUpQuery singUpQuery;

    public SingUpServiceImpl(SingUpQuery singUpQuery) {
        this.singUpQuery = singUpQuery;
    }

    @Override
    public void createSingUp(SingUp singUp, Roles roles) {
        singUpQuery.createSingUp(singUp,roles);
    }
}
