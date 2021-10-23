package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.service.ItemService;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@SpringJUnitConfig(MainConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
class LoadingBeansAndApplicationContextTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void validateLoadingArrayListRepositoryAsBean() {
        ItemRepository arrayListItemRepository = applicationContext.getBean(ItemRepository.class, "getArrayListItemRepository");
        assertThat(arrayListItemRepository, is(notNullValue()));
    }

    @Test
    void validateLoadingLinkedListRepositoryAsBean() {
        ItemRepository linkedListItemRepository = applicationContext.getBean(ItemRepository.class, "getLinkedListItemRepository");
        assertThat(linkedListItemRepository, is(notNullValue()));
    }

    @Test
    void validateLoadingDefaultListRepositoryAsBean() {
        ItemRepository itemRepository = applicationContext.getBean(ItemRepository.class);
        assertThat(itemRepository, is(notNullValue()));
    }

    @Test
    void validateLoadingItemValidatorAsBean() {
        ItemValidator itemValidator = applicationContext.getBean(ItemValidator.class);
        assertThat(itemValidator, is(notNullValue()));
    }

    @Test
    void validateLoadingItemServiceAsBean() {
        ItemService itemService = applicationContext.getBean(ItemService.class);
        assertThat(itemService, is(notNullValue()));
    }

    @Test
    void validateLoadingColorFactoryAsBean() {
        ColorFactory colorFactory = applicationContext.getBean(ColorFactory.class);
        assertThat(colorFactory, is(notNullValue()));
    }

    @Test
    void validateLoadingColorAsBean() {
        Color color = applicationContext.getBean(Color.class);
        assertThat(color, is(notNullValue()));
    }
}