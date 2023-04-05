package com.pwr.weblablibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private int id;
    private String firstName;
    private String lastName;
}
