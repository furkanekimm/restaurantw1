package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
@Getter
@Setter
public class CartDTO extends BaseDTO implements Serializable {
    private Timestamp createDate;
    private  Long piece;
    private Long total;
    private Long productId;
    private Long tableId;
    private Long categoryId;
    private Long waiterId;
    private Long customerId;


}
