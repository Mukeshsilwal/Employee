package com.EmployeeManagement11.employeeManagement.rpc;

import com.EmployeeManagement11.employeeManagement.facad.EmployeeFacade;
import employee.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class EmployeeEndPoint extends EmployeeServiceGrpc.EmployeeServiceImplBase {
    private final EmployeeFacade employeeFacade;


    public EmployeeEndPoint(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @Override
    public void getEmployee(EmployeeRequestOuterClass.EmployeeRequest request, StreamObserver<EmployeeResponseOuterClass.EmployeeResponse> responseObserver) {
        EmployeeResponseOuterClass.EmployeeResponse response = employeeFacade.getEmployee(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void createEmployee(EmployeeOuterClass.Employee request, StreamObserver<EmployeeOuterClass.Employee> responseObserver) {
        employeeFacade.savaEmployee(request);
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteEmployee(EmployeeRequestOuterClass.EmployeeRequest request, StreamObserver<EmployeeRequestOuterClass.EmployeeRequest> responseObserver) {
        employeeFacade.deleteEmployee(request);
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}


