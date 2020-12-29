package com.example.restaurantapii.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@SQLDelete(sql =
        "UPDATE place_rest " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class PlaceRest extends BaseEntity implements Serializable {
    private String name;
    private Long tablePiece;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
}
