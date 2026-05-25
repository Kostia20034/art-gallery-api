package com.example.First.project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    public void setName(String name){this.name = name;}
    public void setPrice(double price){this.price = price;}
    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
}
