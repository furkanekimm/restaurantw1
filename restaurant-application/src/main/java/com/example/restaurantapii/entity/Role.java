package com.example.restaurantapii.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
