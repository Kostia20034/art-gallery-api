package com.example.art_gallery.dto;

import com.example.art_gallery.model.Category;
import java.time.LocalDateTime;

public class ArtworkResponseDTO {

    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private Category category;
    private String medium;
    private double price;
    private boolean featured;
    private boolean available;
    private LocalDateTime createdAt;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
