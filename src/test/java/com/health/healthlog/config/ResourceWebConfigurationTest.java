package com.health.healthlog.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class ResourceWebConfigurationTest {

    @Test
    public void testAddResourceHandlers() {
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
        ResourceHandlerRegistration registration = mock(ResourceHandlerRegistration.class);

        when(registry.addResourceHandler("/resources/**")).thenReturn(registration);
        when(registration.addResourceLocations("classpath:/static/")).thenReturn(registration);

        ResourceWebConfiguration config = new ResourceWebConfiguration();

        config.addResourceHandlers(registry);

        verify(registry).addResourceHandler("/resources/**");
        verify(registration).addResourceLocations("classpath:/static/");
    }

}
