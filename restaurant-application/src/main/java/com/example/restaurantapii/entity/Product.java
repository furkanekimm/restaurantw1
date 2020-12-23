package com.example.restaurantapii.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@SQLDelete(sql =
        "UPDATE product " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
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
    private List<Category> category;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @Override
    public String toString() {
        return productName;
    }
}