package com.epam.edu.spring.core.template.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Item {
    private long id;
    private String name;
    private double price;
    private Color color;
}