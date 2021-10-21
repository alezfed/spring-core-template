package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import com.epam.edu.spring.core.template.validator.SimpleItemValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
        return new ColorFactory().getObject();
    }

    @Bean
    @Lazy
    public ItemValidator getValidator() {
        return new SimpleItemValidator();
    }

    @Bean
    public InjectRandomIntAnnotationBeanPostProcessor getInjectRandomPostProcessor() {
        return new InjectRandomIntAnnotationBeanPostProcessor();
    }
}