package com.example.art_gallery.dto;

import com.example.art_gallery.model.Category;
import jakarta.validation.constraints.*;

public class ArtworkRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description too long")
    private String description;

    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @NotNull(message = "Category is required")
    private Category category;

    @NotBlank(message = "Medium cannot be blank")
    private String medium;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    private boolean featured = false;
    private boolean available = true;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getMedium() { return medium; }
    public void setMedium(String medium) { this.medium = medium; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}