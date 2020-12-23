package com.example.restaurantapii.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
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



    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @Override
    public String toString() {
        return productName;
    }
}