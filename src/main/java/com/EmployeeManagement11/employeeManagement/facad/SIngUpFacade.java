package com.EmployeeManagement11.employeeManagement.facad;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import com.EmployeeManagement11.employeeManagement.service.SingUpService;
import employee.SingUpOuterClass;
import employee.UserRolesOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SIngUpFacade {
    private final SingUpService service;
    private final ModelMapper modelMapper;
    public SIngUpFacade(SingUpService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    public void createSingUp(SingUpOuterClass.SingUp singUp, UserRolesOuterClass.UserRoles roles){
            Roles roles1=toEntityRole(roles);
            SingUp singUp1=toEntity(singUp);
            SingUp singUp2=service.createSingUp(singUp1,roles1);
        toOuterClass(singUp2);

    }

    public com.EmployeeManagement11.employeeManagement.enitiy.SingUp  toEntity(SingUpOuterClass.SingUp singUp){
       return modelMapper.map(singUp,SingUp.class);
    }

    public SingUpOuterClass.SingUp toOuterClass(SingUp singUp) {
        return SingUpOuterClass.SingUp.newBuilder().setName(singUp.getName())
                .setEmail(singUp.getEmail())
                .setPhone(singUp.getPhone())
                .setPassword(singUp.getPassword())
                .build();
    }
    public Roles toEntityRole(UserRolesOuterClass.UserRoles roles){
        return modelMapper.map(roles,Roles.class);
    }
}
