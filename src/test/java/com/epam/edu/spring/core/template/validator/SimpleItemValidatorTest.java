package com.epam.edu.spring.core.template.validator;

import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleItemValidatorTest {
    private final ItemValidator validator = new SimpleItemValidator();
    private final Item verifyItem = new Item(77, "Spesial item", 0.01, Color.PURPLE);

    @Test
    void returnTrueWhenIsItemValidMethodCalled() {
        assertThat(validator.isItemValid(verifyItem), is(true));
    }
}