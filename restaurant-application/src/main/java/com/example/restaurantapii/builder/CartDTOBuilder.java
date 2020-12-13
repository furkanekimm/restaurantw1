package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.CartDTO;

import java.sql.Timestamp;

public class CartDTOBuilder extends Builder {
    private Timestamp createDate;
    private  Long piece;
    private Long total;
    private Long productId;
    private Long tableId;
    private Long categoryId;
    private Long waiterId;

    @Override
    public CartDTO build() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotal(this.total);
        cartDTO.setTableId(this.tableId);
        cartDTO.setProductId(this.productId);
        cartDTO.setWaiterId(this.waiterId);
        cartDTO.setPiece(this.piece);
        cartDTO.setCreateDate(this.createDate);
        cartDTO.setId(super.id);
        cartDTO.setCategoryId(this.categoryId);
        return cartDTO;
    }

    public CartDTOBuilder createDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public CartDTOBuilder piece(Long piece) {
        this.piece = piece;
        return this;
    }

    public CartDTOBuilder total(Long total) {
        this.total = total;
        return this;
    }

    public CartDTOBuilder productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public CartDTOBuilder tableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public CartDTOBuilder categoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public CartDTOBuilder waiterId(Long waiterId) {
        this.waiterId = waiterId;
        return this;
    }

    public CartDTOBuilder id(Long id){
        super.id=id;
        return this;
    }
}
