package org.nickthearchitect.camunda.restdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CamundaSecurityFilter {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean("processEngineAuthenticationFilter")
    public DelegatingFilterProxyRegistrationBean processEngineAuthenticationFilter() {
        DelegatingFilterProxyRegistrationBean registration = new DelegatingFilterProxyRegistrationBean("camundaProcessEngineAuthenticationFilter");
        registration.setApplicationContext(applicationContext);
        registration.setName("camunda-auth");
//        registration.addUrlPatterns("/rest/*");
        registration.addUrlPatterns("/app/*");
        return registration;
    }
}
