package com.epam.edu.spring.core.template.repository;

import com.epam.edu.spring.core.template.configuration.MainConfiguration;
import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringJUnitConfig(MainConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
class ArrayListItemRepositoryTest {
    @Autowired
    @Qualifier("getArrayListItemRepository")
    private ItemRepository repository;
    @Value("${initial.sequence}")
    long valueFromProperties;
    private final Item verifyItem = new Item(valueFromProperties, "Spesial item", 0.01, Color.PURPLE);

    @SneakyThrows
    void directAddVerifyItem() {
        ArrayList<Item> arrayList = new ArrayList();
        arrayList.add(verifyItem);
        Field field = ReflectionUtils.findField(repository.getClass(), "items");
        field.setAccessible(true);
        field.set(repository, arrayList);
    }

    @SneakyThrows
    void clearRepositary() {
        ArrayList<Item> arrayList = new ArrayList();
        Field field = ReflectionUtils.findField(repository.getClass(), "items");
        field.setAccessible(true);
        field.set(repository, arrayList);
    }

    @Test
    void validateCreationItemsFieldAndCallingSetHolderMethod() {
        Field field = ReflectionUtils.findField(repository.getClass(), "items");
        assertThat(field, is(notNullValue()));
    }

    @SneakyThrows
    @Test
    void validateSettingInitialSequenceFieldFromPropertiesFile() {
        Field field = ReflectionUtils.findField(repository.getClass(), "initialSequence");
        long valueFromField = (long) field.get(repository);
        assertThat(valueFromField, is(equalTo(valueFromProperties)));
    }

    @Test
    void returnItemWhenGetByIdMethodCalled() {
        directAddVerifyItem();
        Item findItem = repository.getById(verifyItem.getId());
        assertThat(findItem, is(verifyItem));
    }

    @Test
    void returnNullWhenGetByIdMethodCalledAndItemNotFound() {
        clearRepositary();
        Item findItem = repository.getById(verifyItem.getId());
        assertThat(findItem, is(nullValue()));
    }

    @Test
    void returnTrueWhenCalledCreateItemMethod() {
        clearRepositary();
        repository.createItem(verifyItem);
        Item findItem = repository.getById(verifyItem.getId());
        assertThat(findItem, is(verifyItem));
    }
}