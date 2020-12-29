package com.example.restaurantapii.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "WAITERS")
@SQLDelete(sql =
        "UPDATE waiters " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Inheritance(strategy = InheritanceType.JOINED)
public class Waiter extends BaseEntity implements Serializable {
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;

    @OneToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media media;
}
