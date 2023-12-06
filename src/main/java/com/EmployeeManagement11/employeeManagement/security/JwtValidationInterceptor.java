package com.EmployeeManagement11.employeeManagement.security;

import com.EmployeeManagement11.employeeManagement.service.CustomUserDetailsService;
import io.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtValidationInterceptor implements ServerInterceptor {

    private static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);


    private final JwtService jwtService;
    private final CustomUserDetailsService userDetails;

    public JwtValidationInterceptor(JwtService jwtService, CustomUserDetailsService userDetails) {
        super();
        this.jwtService = jwtService;
        this.userDetails = userDetails;
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler
    ) {
        String token = metadata.get(AUTHORIZATION_METADATA_KEY);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            try {
                String username = this.jwtService.getUsernameFromToken(token);

                if (username != null) {
                    UserDetails userDetails1= userDetails.loadUserByUsername(username);
                    Boolean validateToken = this.jwtService.validationToken(token,userDetails1);

                    if (validateToken) {
                        Context context = Context.current().withValue(Context.key("username"), username);
                        return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        serverCall.close(Status.UNAUTHENTICATED.withDescription("Invalid or missing token"), metadata);
        return new ServerCall.Listener<ReqT>() {};
    }
}
