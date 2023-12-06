package com.EmployeeManagement11.employeeManagement.rpc;

import com.EmployeeManagement11.employeeManagement.facad.SIngUpFacade;
import com.EmployeeManagement11.employeeManagement.security.JwtService;
import employee.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@GrpcService
public class SingUpEndPoint extends LoginLogoutServiceGrpc.LoginLogoutServiceImplBase {

    private final SIngUpFacade sIngUpFacade;
    private final UserDetailsService service;
    private final JwtService service1;

    public SingUpEndPoint(SIngUpFacade sIngUpFacade, UserDetailsService service, JwtService service1) {
        this.sIngUpFacade = sIngUpFacade;
        this.service = service;
        this.service1 = service1;
    }

    @Override
    public void login(LoginOuterClass.Login request, StreamObserver<LoginOuterClass.LoginResponse> responseObserver) {

        UserDetails userDetails= service.loadUserByUsername(request.getName());
       String token=this.service1.generateToken(userDetails);
        LoginOuterClass.LoginResponse response= LoginOuterClass.LoginResponse.newBuilder().setJwt(token).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void register(SingUpOuterClass.SingUp request, StreamObserver<SingUpOuterClass.RegisterResponse> responseObserver) {
        UserRolesOuterClass.UserRoles roles=request.getRoles();
      sIngUpFacade.createSingUp(request,roles);
        SingUpOuterClass.RegisterResponse response= SingUpOuterClass.RegisterResponse.newBuilder().setStatus("UserCreated").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
