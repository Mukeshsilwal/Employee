package com.EmployeeManagement11.employeeManagement.facad;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;
import com.EmployeeManagement11.employeeManagement.service.SignUpService;
import employee.SignUpOuterClass;
import employee.UserRolesOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpFacade {
    private final SignUpService service;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Autowired
    public SignUpFacade(SignUpService service, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void createSingUp(SignUpOuterClass.SignUp signUp, UserRolesOuterClass.UserRoles roles){
            Roles roles1=toEntityRole(roles);
            SignUp signUp1=toEntity(signUp);
            signUp1.setPassword(passwordEncoder.encode(signUp1.getPassword()));
            service.createSignUp(signUp1,roles1);


    }

    public SignUp toEntity(SignUpOuterClass.SignUp singUp){
       return modelMapper.map(singUp, SignUp.class);
    }

   public SignUpOuterClass.RegisterResponse toResponse(SignUp singUp){
        return modelMapper.map(singUp, SignUpOuterClass.RegisterResponse.class);
   }
    public Roles toEntityRole(UserRolesOuterClass.UserRoles roles){
        return modelMapper.map(roles,Roles.class);
    }
}
