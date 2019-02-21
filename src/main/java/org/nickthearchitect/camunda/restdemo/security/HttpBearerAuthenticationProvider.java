package org.nickthearchitect.camunda.restdemo.security;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpBearerAuthenticationProvider implements AuthenticationProvider {
    private HttpBasicAuthenticationProvider basicAuthenticationProvider = new HttpBasicAuthenticationProvider();

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        return new AuthenticationResult("service-consumer", true);
    }

    @Override
    public void augmentResponseByAuthenticationChallenge(HttpServletResponse response, ProcessEngine engine) {
        basicAuthenticationProvider.augmentResponseByAuthenticationChallenge(response, engine);
    }
}
