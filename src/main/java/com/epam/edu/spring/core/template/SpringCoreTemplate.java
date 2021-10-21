package com.epam.edu.spring.core.template;

import com.epam.edu.spring.core.template.configuration.ColorFactory;
import com.epam.edu.spring.core.template.configuration.MainConfiguration;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.service.ItemService;
import com.epam.edu.spring.core.template.service.SimpleItemService;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringCoreTemplate {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        MainConfiguration configuration = context.getBean(MainConfiguration.class);
        ItemRepository repository = context.getBean(ItemRepository.class);
        ItemValidator validator = context.getBean(ItemValidator.class);
        ColorFactory factory = context.getBean(ColorFactory.class);
        ItemService service = context.getBean(SimpleItemService.class);
    }
}