package com.EmployeeManagement11.employeeManagement.config;

import com.EmployeeManagement11.employeeManagement.rpc.EmployeeEndPoint;
import com.EmployeeManagement11.employeeManagement.rpc.SignUpEndPoint;
import com.EmployeeManagement11.employeeManagement.security.JwtService;
import com.EmployeeManagement11.employeeManagement.security.JwtValidationInterceptor;
import com.EmployeeManagement11.employeeManagement.service.CustomUserDetailsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MyServerConfig {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetails;

    public MyServerConfig(JwtService jwtService, CustomUserDetailsService userDetails) {
        this.jwtService = jwtService;
        this.userDetails = userDetails;
    }

    @Bean
    public Server server(EmployeeEndPoint endPoint, SignUpEndPoint signUpEndPoint) throws IOException {
        Server server= ServerBuilder.forPort(8088).addService(endPoint).addService(signUpEndPoint).intercept(new JwtValidationInterceptor(jwtService,userDetails)).build();
        server.start();
        return server;
    }

}
