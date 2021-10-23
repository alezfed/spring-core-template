package com.epam.edu.spring.core.template.repository;

import com.epam.edu.spring.core.template.configuration.InjectRandomInt;
import com.epam.edu.spring.core.template.configuration.MainConfiguration;
import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

@SpringJUnitConfig(MainConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
class LinkedListItemRepositoryTest {
    @Autowired
    @Qualifier("getLinkedListItemRepository")
    private ItemRepository repository;
    private final Item verifyItem = new Item(77, "Spesial item", 0.01, Color.PURPLE);

    @SneakyThrows
    void directAddVerifyItem() {
        LinkedList<Item> linkedList = new LinkedList<>();
        linkedList.add(verifyItem);
        Field field = ReflectionUtils.findField(repository.getClass(), "items");
        field.setAccessible(true);
        field.set(repository, linkedList);
    }

    @SneakyThrows
    void clearRepositary() {
        LinkedList<Item> linkedList = new LinkedList<>();
        Field field = ReflectionUtils.findField(repository.getClass(), "items");
        field.setAccessible(true);
        field.set(repository, linkedList);
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
        Method method = ReflectionUtils.findMethod(repository.getClass(), "setInitialSequence", int.class);
        InjectRandomInt annotation = method.getAnnotation(InjectRandomInt.class);
        long min = annotation.min();
        long max = annotation.max();
        assertThat(valueFromField, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max)));
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