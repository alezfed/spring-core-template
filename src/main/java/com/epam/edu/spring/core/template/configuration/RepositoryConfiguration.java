package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.repository.ArrayListItemRepository;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.repository.LinkedListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RepositoryConfiguration {
    private ItemRepository repository;
    @Value("${item.repository.implementation}")
    private String repositoryType;

    @Bean
    public ItemRepository getArrayListItemRepository() {
        return new ArrayListItemRepository();
    }

    @Bean
    public ItemRepository getLinkedListItemRepository() {
        return new LinkedListItemRepository();
    }

    @PostConstruct
    public void initRepositoryConfiguration() {
        repository = getArrayListItemRepository();
        if (repositoryType.equals("linked")) {
            repository = getLinkedListItemRepository();
        }
    }

    @Bean(name = "repository")
    public ItemRepository getRepository() {
        return repository;
    }
}