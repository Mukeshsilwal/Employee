package com.EmployeeManagement11.employeeManagement.config;

import com.EmployeeManagement11.employeeManagement.rpc.EmployeeEndPoint;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MyServerConfig {

    @Bean
    public Server server(EmployeeEndPoint endPoint) throws IOException {
        Server server= ServerBuilder.forPort(8088).addService(endPoint).build();
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
