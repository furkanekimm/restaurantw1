package com.example.restaurantapii.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Data
@Entity
@SQLDelete(sql =
        "UPDATE place_rest " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
public class PlaceRest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long tablePiece;


}
