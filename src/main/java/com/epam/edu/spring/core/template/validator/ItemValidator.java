package com.epam.edu.spring.core.template.validator;

import com.epam.edu.spring.core.template.entity.Item;

public interface ItemValidator {

    boolean isItemValid(Item item);
}