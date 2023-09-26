package com.testing.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private String name;
    private String trademark;
    private int stock;
    private double price;
    private String description;
    private String tags;
    private boolean is_active;
    private String id;
}
