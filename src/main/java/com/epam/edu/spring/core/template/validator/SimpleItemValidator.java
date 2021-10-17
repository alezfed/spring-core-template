package com.epam.edu.spring.core.template.validator;

import com.epam.edu.spring.core.template.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleItemValidator implements ItemValidator {

    @Autowired
    public SimpleItemValidator() {
    }

    @Override
    public boolean isItemValid(Item item) {
        return true;
    }
}