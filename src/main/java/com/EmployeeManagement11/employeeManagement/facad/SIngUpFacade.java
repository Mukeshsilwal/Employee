package com.EmployeeManagement11.employeeManagement.facad;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import com.EmployeeManagement11.employeeManagement.service.SingUpService;
import employee.SingUpOuterClass;
import employee.UserRolesOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SIngUpFacade {
    private final SingUpService service;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Autowired
    public SIngUpFacade(SingUpService service, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void createSingUp(SingUpOuterClass.SingUp singUp, UserRolesOuterClass.UserRoles roles){
            Roles roles1=toEntityRole(roles);
            SingUp singUp1=toEntity(singUp);
            singUp1.setPassword(passwordEncoder.encode(singUp1.getPassword()));
            service.createSingUp(singUp1,roles1);


    }

    public com.EmployeeManagement11.employeeManagement.enitiy.SingUp  toEntity(SingUpOuterClass.SingUp singUp){
       return modelMapper.map(singUp,SingUp.class);
    }

   public SingUpOuterClass.RegisterResponse toResponse(SingUp singUp){
        return modelMapper.map(singUp, SingUpOuterClass.RegisterResponse.class);
   }
    public Roles toEntityRole(UserRolesOuterClass.UserRoles roles){
        return modelMapper.map(roles,Roles.class);
    }
}
