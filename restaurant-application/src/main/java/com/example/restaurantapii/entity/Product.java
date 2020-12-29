package com.example.restaurantapii.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends BaseEntity implements Serializable {
    private String productName;
    private String description;
    private Long price;

    @JsonManagedReference
    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<Category> category;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @Override
    public String toString() {
        return productName;
    }
}