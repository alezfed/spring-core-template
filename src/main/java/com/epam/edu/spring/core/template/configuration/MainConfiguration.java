package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.service.ItemService;
import com.epam.edu.spring.core.template.service.SimpleItemService;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import com.epam.edu.spring.core.template.validator.SimpleItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

@Configuration
@ComponentScan
@Import({RepositoryConfiguration.class, InitializerConfiguration.class})
@PropertySource("classpath:application.properties")
public class MainConfiguration {
    @Resource
    @Autowired
    @Qualifier("repository")
    public ItemRepository repository;

    public ColorFactory colorFactory;
    @Resource
    public ItemValidator validator;
    @Resource
    public ItemService service;

    @Bean
    public ItemValidator getValidator() {
        return new SimpleItemValidator();
    }

    @Bean
    public ItemService getService() {
        return new SimpleItemService(repository, validator, colorFactory);
    }

    @PostConstruct
    public void addRandomNewItemsInRepository() {
        int countNewItem = new Random().nextInt(2) + 10;
        for (int i = 0; i < countNewItem; i++) {
            service.createItem(new Item());
        }
    }
}