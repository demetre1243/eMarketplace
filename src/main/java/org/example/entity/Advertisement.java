package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;
    private LocalDateTime submittionTime;
    private String photoUrl;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getSubmittionTime() {
        return submittionTime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubmittionTime(LocalDateTime submittionTime) {
        this.submittionTime = submittionTime;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
