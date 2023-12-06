package com.EmployeeManagement11.employeeManagement.rpc;

import com.EmployeeManagement11.employeeManagement.facad.SIngUpFacade;
import com.EmployeeManagement11.employeeManagement.security.JwtService;
import com.EmployeeManagement11.employeeManagement.service.CustomUserDetailsService;
import employee.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.CredentialException;

@GrpcService
public class SingUpEndPoint extends LoginLogoutServiceGrpc.LoginLogoutServiceImplBase {

    private final SIngUpFacade sIngUpFacade;
    private final CustomUserDetailsService service;
    private final JwtService service1;
    private final PasswordEncoder passwordEncoder;

    public SingUpEndPoint(SIngUpFacade sIngUpFacade, CustomUserDetailsService service, JwtService service1, PasswordEncoder passwordEncoder) {
        this.sIngUpFacade = sIngUpFacade;
        this.service = service;
        this.service1 = service1;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void login(LoginOuterClass.Login request, StreamObserver<LoginOuterClass.LoginResponse> responseObserver) throws BadCredentialsException {
        String token=null;
        try {
           UserDetails userDetails = service.loadUserByUsername(request.getName());
           System.out.println(userDetails);
           BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
           if (encoder.matches(request.getPassword(), userDetails.getPassword())){
                token=this.service1.generateToken(userDetails);
           }
       }
       catch (Exception e){
           e.printStackTrace();

       }
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
//    public boolean matches(String enteredPassword, String storedHashedPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(enteredPassword, storedHashedPassword);
//    }

}
