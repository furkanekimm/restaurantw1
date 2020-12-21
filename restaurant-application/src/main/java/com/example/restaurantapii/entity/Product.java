package com.example.restaurantapii.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String description;
    private Long price;
    private String urlToImage;

    @JsonManagedReference
    @ManyToMany(mappedBy = "products")
    private Set<Category> category;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @Override
    public String toString() {
        return productName;
    }
}