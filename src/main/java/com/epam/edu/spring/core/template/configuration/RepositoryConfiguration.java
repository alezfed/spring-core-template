package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.repository.ArrayListItemRepository;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.repository.LinkedListItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

public class RepositoryConfiguration {
    @Value("${item.repository.implementation}")
    private String repositoryType;

    @Bean
    @Lazy
    public ItemRepository getArrayListItemRepository() {
        return new ArrayListItemRepository();
    }

    @Bean
    @Lazy
    public ItemRepository getLinkedListItemRepository() {
        return new LinkedListItemRepository();
    }

    @Bean
    @Primary
    public ItemRepository getRepository() {
        if (repositoryType.equals("linked")) {
            return new LinkedListItemRepository();
        }
        return new ArrayListItemRepository();
    }
}