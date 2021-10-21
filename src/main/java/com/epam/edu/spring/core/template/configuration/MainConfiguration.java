package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.service.ItemService;
import com.epam.edu.spring.core.template.service.SimpleItemService;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Random;

@Configuration
@Import({RepositoryConfiguration.class, InitializerConfiguration.class})
@PropertySource("classpath:application.properties")
@ToString
public class MainConfiguration {
    @Autowired
    private ItemRepository repository;
    @Autowired
    private ItemValidator validator;
    @Autowired
    @Lazy
    private ColorFactory colorFactory;

    @Bean
    public ItemService getService() {
        return new SimpleItemService(repository, validator, colorFactory);
    }

    @PostConstruct
    public void addRandomNewItemsInRepository() {
        int countNewItem = new Random().nextInt(2) + 10;
        for (int i = 0; i < countNewItem; i++) {
            getService().createItem(new Item());
        }
    }
}