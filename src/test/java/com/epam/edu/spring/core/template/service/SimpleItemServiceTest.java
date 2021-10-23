package com.epam.edu.spring.core.template.service;

import com.epam.edu.spring.core.template.configuration.ColorFactory;
import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ArrayListItemRepository;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import com.epam.edu.spring.core.template.validator.SimpleItemValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static net.obvj.junit.utils.matchers.AdvancedMatchers.throwsException;

class SimpleItemServiceTest {
    private final ItemRepository repository = Mockito.mock(ArrayListItemRepository.class);
    private final ItemValidator validator = Mockito.mock(SimpleItemValidator.class);
    private final ColorFactory colorFactory = Mockito.mock(ColorFactory.class);
    private final ItemService service = new SimpleItemService(repository, validator, colorFactory);
    private final long itemId = 77;
    private final Item verifyItem = new Item(itemId, "Spesial item", 0.01, Color.PURPLE);

    @Test
    void returnItemWhenGetByIdItemMethodCalled() {
        Mockito.when(repository.getById(itemId)).thenReturn(verifyItem);
        Item findItem = service.getById(itemId);
        assertThat(findItem, is(verifyItem));
        Mockito.verify(repository).getById(itemId);
    }

    @SneakyThrows
    @Test
    void returnTrueWhenCreateItemMethodCalledAndItemIsValid() {
        Mockito.when(validator.isItemValid(verifyItem)).thenReturn(true);
        Mockito.when(colorFactory.getObject()).thenReturn(Color.PURPLE);
        Mockito.when(repository.createItem(Mockito.any(Item.class))).thenReturn(true);
        assertThat(service.createItem(verifyItem), is(true));
        Mockito.verify(validator).isItemValid(verifyItem);
        Mockito.verify(colorFactory).getObject();
        Mockito.verify(repository).createItem(Mockito.any(Item.class));
    }

    @SneakyThrows
    @Test
    void returnFalseWhenCreateItemMethodCalledAndItemIsNotValid() {
        Mockito.when(validator.isItemValid(verifyItem)).thenReturn(false);
        Mockito.when(colorFactory.getObject()).thenReturn(Color.PURPLE);
        Mockito.when(repository.createItem(Mockito.any(Item.class))).thenReturn(true);
        assertThat(service.createItem(verifyItem), is(false));
        Mockito.verify(validator).isItemValid(verifyItem);
        Mockito.verify(colorFactory, Mockito.never()).getObject();
        Mockito.verify(repository, Mockito.never()).createItem(Mockito.any(Item.class));
    }

    @SneakyThrows
    @Test
    void returnExceptionWhenCreateItemMethodCalledAndItemIsValidAndColorFactoryIsBroken() {
        Mockito.when(validator.isItemValid(verifyItem)).thenReturn(true);
        Mockito.when(colorFactory.getObject()).thenThrow(new NoSuchElementException("Error occurred"));
        Mockito.when(repository.createItem(Mockito.any(Item.class))).thenReturn(true);
        assertThat(() -> service.createItem(verifyItem), throwsException(NoSuchElementException.class));
        Mockito.verify(validator).isItemValid(verifyItem);
        Mockito.verify(colorFactory).getObject();
        Mockito.verify(repository, Mockito.never()).createItem(Mockito.any(Item.class));
    }
}