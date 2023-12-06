package com.EmployeeManagement11.employeeManagement.exception;

import io.grpc.Status;

public class ApiResponse {

    private String name;
    private Status status;

    public ApiResponse(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
