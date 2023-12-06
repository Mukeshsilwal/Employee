package com.EmployeeManagement11.employeeManagement.enitiy;

import employee.UserRolesOuterClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Employee{
    private int id;
    private String name;
    private String phone;
    private String password;
    private String emergency_contact;
   private String citizenship;
   private int date;
    private int contact_renew_date;
    private String contract_doc;

    private List<UserRolesOuterClass.UserRoles> userRoles;

    public Employee() {
    }

    public Employee(int id, String name, String phone, String emergency_contact, String citizenship, int date, int contact_renew_date, String contract_doc,String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.emergency_contact = emergency_contact;
        this.citizenship = citizenship;
        this.date = date;
        this.contact_renew_date = contact_renew_date;
        this.contract_doc = contract_doc;
        this.password=password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getContact_renew_date() {
        return contact_renew_date;
    }

    public void setContact_renew_date(int contact_renew_date) {
        this.contact_renew_date = contact_renew_date;
    }

    public String getContract_doc() {
        return contract_doc;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContract_doc(String contract_doc) {
        this.contract_doc = contract_doc;
    }
}
