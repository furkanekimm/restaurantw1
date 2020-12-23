package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.ProductDTO;

import java.util.List;

public class ProductDTOBuilder extends Builder {
    private String productName;
    private String description;
    private Long price;
    private String urlToImage;
    private List<Long> categoryId;
    private String categoryName;



    @Override
    public ProductDTO build() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(super.id);
        productDTO.setProductName(this.productName);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        productDTO.setUrlToImage(this.urlToImage);
        //productDTO.set(this.categoryId);
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

    public ProductDTOBuilder urlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public ProductDTOBuilder categoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ProductDTOBuilder categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
