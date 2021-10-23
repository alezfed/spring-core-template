package com.epam.edu.spring.core.template.service;

import com.epam.edu.spring.core.template.configuration.ColorFactory;
import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Random;

@ToString
@AllArgsConstructor
public class SimpleItemService implements ItemService {
    private final Random random = new Random();
    private ItemRepository itemRepository;
    private ItemValidator itemValidator;
    private ColorFactory colorFactory;

    @Override
    public Item getById(long id) {
        return itemRepository.getById(id);
    }

    @Override
    public boolean createItem(Item item) {
        if (itemValidator.isItemValid(item)) {
            try {
                Color color = colorFactory.getObject();
                item.setColor(color);
                item.setName("It is name of " + color.toString().toLowerCase());
                item.setPrice(random.nextInt(500) + 200);
            } catch (Exception exception) {
                throw new NoSuchElementException("An error occurred while a new item adds in the service.");
            }
            return itemRepository.createItem(item);
        }
        return false;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setItemValidator(ItemValidator itemValidator) {
        this.itemValidator = itemValidator;
    }

    @Autowired
    public void setColorFactory(ColorFactory colorFactory) {
        this.colorFactory = colorFactory;
    }
}