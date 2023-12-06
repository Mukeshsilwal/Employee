package com.EmployeeManagement11.employeeManagement.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    // No-argument constructor
    public BearerTokenAuthenticationToken() {
        super(null);
        setAuthenticated(false);
    }

    public BearerTokenAuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        setAuthenticated(true);
    }

    public BearerTokenAuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }


    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null; // Adjust based on your needs
    }
}
