package com.epam.edu.spring.core.template.validator;

import com.epam.edu.spring.core.template.entity.Item;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class SimpleItemValidator implements ItemValidator {

    @Override
    public boolean isItemValid(Item item) {
        return true;
    }
}