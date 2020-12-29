package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.ProductDTO;

import java.util.List;

public class ProductDTOBuilder extends Builder {
    private String productName;
    private String description;
    private Long price;
    private List<Long> categoryId;

    @Override
    public ProductDTO build() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(super.id);
        productDTO.setProductName(this.productName);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        productDTO.setCategoryId(this.categoryId);
        //productDTO.setCategoryName(this.categoryName);
        return productDTO;
    }

    public ProductDTOBuilder id(Long id){
        super.id = id;
        return this;
    }

    public ProductDTOBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductDTOBuilder price(Long price) {
        this.price = price;
        return this;
    }

    public ProductDTOBuilder categoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
        return this;
    }

}
