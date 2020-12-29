package com.example.restaurantapii.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        "UPDATE media " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media extends BaseEntity implements Serializable {
    private String name;
    @Column(length = 1000000)
    private byte[] fileContent;

   /* @JsonIgnore
    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    private List<Category> categories;

    @OneToOne(mappedBy = "media")
    private Waiter waiter;*/



}
