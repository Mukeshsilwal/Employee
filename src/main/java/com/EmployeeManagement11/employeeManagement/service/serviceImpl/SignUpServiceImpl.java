package com.EmployeeManagement11.employeeManagement.service.serviceImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;
import com.EmployeeManagement11.employeeManagement.repo.SignUpQuery;
import com.EmployeeManagement11.employeeManagement.service.SignUpService;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final SignUpQuery signUpQuery;

    public SignUpServiceImpl(SignUpQuery signUpQuery) {
        this.signUpQuery = signUpQuery;
    }

    @Override
    public void createSignUp(SignUp singUp, Roles roles) {
        signUpQuery.createSignUp(singUp,roles);
    }
}
