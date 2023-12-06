package com.EmployeeManagement11.employeeManagement.facad;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.service.EmployeeService;
import employee.EmployeeOuterClass;
import employee.EmployeeRequestOuterClass;
import employee.EmployeeResponseOuterClass;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFacade {
    private final EmployeeService employeeService;

    public EmployeeFacade( EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    public void savaEmployee(EmployeeOuterClass.Employee employee) {
        Employee employee1 = mapProtoToEmployee(employee);
        employeeService.saveEmployee(employee1);

    }

    public void deleteEmployee(EmployeeRequestOuterClass.EmployeeRequest request) {
        int id = request.getId();
        employeeService.deleteEmployee(id);
    }

    public EmployeeResponseOuterClass.EmployeeResponse getEmployee(EmployeeRequestOuterClass.EmployeeRequest request) {
        int id = request.getId();
        Employee employee = employeeService.getEmployee(id);
        return toResponse(employee);

    }

    public EmployeeResponseOuterClass.EmployeeResponse updateEmployee(EmployeeOuterClass.Employee employee) {
        Employee employee1 = mapProtoToEmployee(employee);
        Employee employee2 = employeeService.updateEmployee(employee1);
        return toProto(employee2);


    }

    public EmployeeResponseOuterClass.EmployeeResponse toProto(Employee employee) {
        return EmployeeResponseOuterClass.EmployeeResponse.newBuilder().setId(employee.getId()).
                setName(employee.getName()).
                setPhone(employee.getPhone()).
                setEmergencyContact(employee.getEmergency_contact()).setCitizenship(employee.getCitizenship())
                .setDate(employee.getDate())
                .setContactRenewDate(employee.getContact_renew_date())
                .build();
    }

    public Employee mapProtoToEmployee(EmployeeOuterClass.Employee employee) {
        int id = employee.getId();
        String name = employee.getName();
        String phone = employee.getPhone();
        String emergency = employee.getEmergencyContact();
        String citizen = employee.getCitizenship();
        int date = employee.getDate();
        int contractRenew =employee.getContactRenewDate();
        String contactDoc= employee.getContractDoc();
        String pass=employee.getPassword();
        return new Employee(id,name,phone,emergency,citizen,date,contractRenew,contactDoc,pass);
    }
    public EmployeeResponseOuterClass.EmployeeResponse toResponse(Employee employee){
        return EmployeeResponseOuterClass.EmployeeResponse.newBuilder().setId(employee.getId())
                .setName(employee.getName())
                .setPhone(employee.getPhone())
                .setEmergencyContact(employee.getEmergency_contact())
                .setCitizenship(employee.getEmergency_contact())
                .setDate(employee.getDate())
                .setContactRenewDate(employee.getContact_renew_date())
                .build();
    }
}