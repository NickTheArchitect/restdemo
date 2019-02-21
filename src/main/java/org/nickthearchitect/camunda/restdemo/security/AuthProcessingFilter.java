package org.nickthearchitect.camunda.restdemo.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

public class AuthProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    public Object getPreAuthenticatedPrincipal(HttpServletRequest request){
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request){
        return "N/A";
    }
}
