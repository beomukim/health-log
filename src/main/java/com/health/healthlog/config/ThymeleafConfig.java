package com.health.healthlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(true); // decoupled-logic : true

        return defaultTemplateResolver;
    }

}
