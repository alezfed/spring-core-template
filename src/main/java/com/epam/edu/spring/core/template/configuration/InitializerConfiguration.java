package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class InitializerConfiguration {
    @Bean
    @Lazy
    public ColorFactory getColorFactory() {
        return new ColorFactory();
    }

    @Bean
    @Lazy
    @Scope("prototype")
    public Color getColor() throws Exception {
        return getColorFactory().getObject();
    }
}