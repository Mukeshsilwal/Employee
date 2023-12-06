package com.EmployeeManagement11.employeeManagement.exception;

import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GlobalException {

    @GrpcExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse  statusException(ResourceNotFoundException e){
       String msg=e.getMessage();
        return new ApiResponse(msg,Status.NOT_FOUND);
    }
}
