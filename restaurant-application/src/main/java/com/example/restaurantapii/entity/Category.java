package com.example.restaurantapii.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String urlToImage;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name ="CATEGORY_PRODUCTS",joinColumns = @JoinColumn(name = "category_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;


}
