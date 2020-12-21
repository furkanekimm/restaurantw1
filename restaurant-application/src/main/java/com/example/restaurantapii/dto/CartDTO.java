package com.example.restaurantapii.dto;



import lombok.Data;

import java.sql.Timestamp;
@Data
public class CartDTO {
    private Long id;
    private Timestamp createDate;
    private  Long piece;
    private Long total;
    private Long productId;
    private Long tableId;
    private Long categoryId;
    private Long waiterId;


}
