//package com.EmployeeManagement11.employeeManagement.config;
//
//import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
//import employee.EmployeeOuterClass;
//import employee.EmployeeResponseOuterClass;
//import org.mapstruct.Mapper;
//import org.mapstruct.MapperConfig;
//import org.mapstruct.factory.Mappers;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//@Mapper
//@Service
//public interface EmployeeMapper {
//    EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);
//
//    public Employee mapProtoToEmployee(EmployeeOuterClass.Employee employee);
//    public EmployeeOuterClass.Employee employeeToProto(Employee employee);
//    public EmployeeResponseOuterClass.EmployeeResponse entityToResponse(Employee employee);
//}
