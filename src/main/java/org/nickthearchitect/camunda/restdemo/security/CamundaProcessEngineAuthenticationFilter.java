package org.nickthearchitect.camunda.restdemo.security;

import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Derivative of the standard Camunda Filter which is more suitable for Spring injection
 * of the AuthenticationProvider. If the AuthenticationProvider is not injected, it will
 * default to using the original initialization method to inject the provider.
 *
 */
@Component
public class CamundaProcessEngineAuthenticationFilter extends ProcessEngineAuthenticationFilter {
    CamundaProcessEngineAuthenticationFilter(AuthenticationProvider authenticationProvider) {
        super();
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (this.authenticationProvider != null) {
            servletPathPrefix = filterConfig.getInitParameter(SERVLET_PATH_PREFIX);
        } else {
            super.init(filterConfig);
        }
    }
}
