package com.EmployeeManagement11.employeeManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {
    private final String secret="\n" +
            "\n" +
            "655468576D5A7133743677397A24432646294A404E635266556A586E32723575";

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver) {

        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return  Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSingInKey() {
        byte[] keysbyte= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keysbyte);
    }

    private boolean isTokenExpired(String token){

        final Date expiration=getExpirationFromToken(token);
        return expiration.before(new Date());
    }
    private Date getExpirationFromToken(String token) {

        return getClaimsFromToken(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails);

    }


    private String doGenerateToken(Map<String, Object> claims, UserDetails userDetails) {
        String authorities=userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*24*60))
                .claim("auth",authorities)
                .signWith(getSingInKey(), SignatureAlgorithm.HS256).compact();
    }
    public Boolean validationToken(String token,UserDetails userDetails){

        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()))&&!isTokenExpired(token);
    }
}
