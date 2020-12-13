package com.example.restaurantapii.entity;

import com.example.restaurantapii.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
