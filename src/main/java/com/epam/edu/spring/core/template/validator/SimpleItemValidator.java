package com.epam.edu.spring.core.template.validator;

import com.epam.edu.spring.core.template.entity.Item;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleItemValidator implements ItemValidator {

    public SimpleItemValidator() {
    }

    @Override
    public boolean isItemValid(Item item) {
        return true;
    }
}