package com.EmployeeManagement11.employeeManagement.config;

import com.EmployeeManagement11.employeeManagement.rpc.EmployeeEndPoint;
import com.EmployeeManagement11.employeeManagement.rpc.SingUpEndPoint;
import com.EmployeeManagement11.employeeManagement.security.GrpcAuthenticationInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MyServerConfig {

    @Bean
    public Server server(EmployeeEndPoint endPoint,SingUpEndPoint singUpEndPoint) throws IOException {
        Server server= ServerBuilder.forPort(8088).addService(endPoint).addService(singUpEndPoint).intercept(new GrpcAuthenticationInterceptor()).build();
        server.start();
        return server;
    }

//   @Bean
//    public EmployeeMapper mapper(){
//        return new EmployeeMapperImpl();
//}


    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
