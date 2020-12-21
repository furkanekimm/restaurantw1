package com.example.restaurantapii.entity;

import com.example.restaurantapii.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 1000000)
    private byte[] fileContent;

    @JsonIgnore
    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToOne(mappedBy = "media")
    private Waiter waiter;


}
