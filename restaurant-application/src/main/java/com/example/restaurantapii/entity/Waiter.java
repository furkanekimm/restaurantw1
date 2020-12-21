package com.example.restaurantapii.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity(name = "WAITERS")
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media media;

}
