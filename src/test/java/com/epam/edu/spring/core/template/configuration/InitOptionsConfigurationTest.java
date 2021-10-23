package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ArrayListItemRepository;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.repository.LinkedListItemRepository;
import com.epam.edu.spring.core.template.service.ItemService;
import com.epam.edu.spring.core.template.service.SimpleItemService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class InitOptionsConfigurationTest {
    private final ItemService service = Mockito.mock(SimpleItemService.class);
    private final MainConfiguration mainConfiguration = new MainConfiguration();
    private final RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();

    @SneakyThrows
    @Test
    void validateAddRandomNewItemsInRepositoryMethodInMainConfiguration() {
        Mockito.when(service.createItem(Mockito.any(Item.class))).thenReturn(true);
        Field field = ReflectionUtils.findField(mainConfiguration.getClass(), "service");
        field.setAccessible(true);
        field.set(mainConfiguration, service);
        mainConfiguration.addRandomNewItemsInRepository();
        Mockito.verify(service, Mockito.atLeast(1)).createItem(Mockito.any(Item.class));
    }

    @SneakyThrows
    @Test
    void checkedRepositoryTypeOnArrayAndValidateGetRepositoryMethodInRepositoryConfigurationClass() {
        Field field = ReflectionUtils.findField(repositoryConfiguration.getClass(), "repositoryType");
        field.setAccessible(true);
        field.set(repositoryConfiguration, "array");
        ItemRepository repository = repositoryConfiguration.getRepository();
        assertThat(repository, is(instanceOf(ArrayListItemRepository.class)));
    }

    @SneakyThrows
    @Test
    void checkedRepositoryTypeOnLinkedAndValidateGetRepositoryMethodInRepositoryConfigurationClass() {
        Field field = ReflectionUtils.findField(repositoryConfiguration.getClass(), "repositoryType");
        field.setAccessible(true);
        field.set(repositoryConfiguration, "linked");
        ItemRepository repository = repositoryConfiguration.getRepository();
        assertThat(repository, is(instanceOf(LinkedListItemRepository.class)));
    }
}