package com.EmployeeManagement11.employeeManagement.config;
import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.security.BearerTokenAuthenticationToken;

import com.EmployeeManagement11.employeeManagement.security.JwtService;
import com.EmployeeManagement11.employeeManagement.service.CustomUserDetailsService;
import employee.EmployeeServiceGrpc;
import employee.LoginLogoutServiceGrpc;
import employee.UserRolesOuterClass;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.check.AccessPredicate;
import net.devh.boot.grpc.server.security.check.AccessPredicateVoter;
import net.devh.boot.grpc.server.security.check.GrpcSecurityMetadataSource;
import net.devh.boot.grpc.server.security.check.ManualGrpcSecurityMetadataSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String GET_EMPLOYEE_METHOD = "getEmployee";
    private static final String DELETE_EMPLOYEE_METHOD = "deleteEmployee";
    private static final String CREATE_EMPLOYEE_METHOD = "createEmployee";
    private static final String REGISTER_METHOD = "register";
    private static final String LOGIN_METHOD = "login";
    private static final String UPDATE_EMPLOYEE_METHOD = "updateEmployee";


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    GrpcAuthenticationReader authenticationReader() {
        final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        readers.add(new BearerAuthenticationReader(BearerTokenAuthenticationToken::new));
        return new CompositeGrpcAuthenticationReader(readers);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

@Bean
public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
}

    @Bean
    AuthenticationManager authenticationManager() {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider());
        return new ProviderManager(providers);
    }

    @Bean
    AccessDecisionManager accessDecisionManager() {
        final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(new AccessPredicateVoter());
        return new UnanimousBased(voters);
    }
    @Bean
    GrpcSecurityMetadataSource grpcSecurityMetadataSource() {
        final ManualGrpcSecurityMetadataSource source = new ManualGrpcSecurityMetadataSource();
        source.set(EmployeeServiceGrpc.getGetEmployeeMethod(), AccessPredicate.hasRole(Roles.ADMIN.name()));
        source.set(EmployeeServiceGrpc.getDeleteEmployeeMethod(), AccessPredicate.hasRole(Roles.ADMIN.name()));
        source.set(EmployeeServiceGrpc.getCreateEmployeeMethod(), AccessPredicate.hasAllRoles(Roles.ADMIN.name(), Roles.USER.name()));
        source.set(LoginLogoutServiceGrpc.getRegisterMethod(), AccessPredicate.permitAll());
        source.set(LoginLogoutServiceGrpc.getLoginMethod(), AccessPredicate.permitAll());
        source.set(EmployeeServiceGrpc.getUpdateEmployeeMethod(), (auth, call) -> "ADMIN".equals(auth.getName()));
        source.setDefault(AccessPredicate.denyAll());
        return source;
    }
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
