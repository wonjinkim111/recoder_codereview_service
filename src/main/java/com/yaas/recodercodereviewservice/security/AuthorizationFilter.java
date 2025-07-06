package com.yaas.recodercodereviewservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Environment env;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
        super(authenticationManager);
        this.env = env;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(this.env.getProperty("authorization.token.header.name"));
        if (authorizationHeader != null && authorizationHeader.startsWith(this.env.getProperty("authorization.token.header.prefix"))) {
            UsernamePasswordAuthenticationToken authentication = this.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(this.env.getProperty("authorization.token.header.name"));
        if (authorizationHeader == null) {
            return null;
        } else {
            String token = authorizationHeader.replace(this.env.getProperty("authorization.token.header.prefix"), "");
            String userId = ((Claims)Jwts.parser().setSigningKey(this.env.getProperty("token.secret")).parseClaimsJws(token.trim()).getBody()).getSubject();
            return userId == null ? null : new UsernamePasswordAuthenticationToken(userId, (Object)null, new ArrayList());
        }
    }
}
