package com.example.restaurantapii.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp createDate;
    private  Long piece;
    private Long total;
    private Long productId;
    private Long tableId;
    private Long categoryId;
    private Long waiterId;


}