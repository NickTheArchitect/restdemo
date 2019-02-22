package org.nickthearchitect.camunda.restdemo;

import org.apache.camel.CamelContext;
import org.camunda.bpm.camel.common.CamelService;
import org.camunda.bpm.camel.spring.CamelServiceImpl;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfiguration {

    @Bean
    CamelService camel(CamelContext camelContext, ProcessEngine processEngine) {
        CamelServiceImpl camelService = new CamelServiceImpl();
        camelService.setProcessEngine(processEngine);
        camelService.setCamelContext(camelContext);

        // Set up the Camel context to use Jackson for POJO <-> List conversion
        // TODO - find a home for this in the application.yaml
        camelContext.getGlobalOptions().put("CamelJacksonEnableTypeConverter", "true");
        return camelService;
    }
}