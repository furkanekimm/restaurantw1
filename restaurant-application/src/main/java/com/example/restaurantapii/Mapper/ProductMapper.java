package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    Product toEntity(ProductDTO productDTO);


    ProductDTO toDTO(Product product);


    List<Product> toEntityList(List<ProductDTO> productDTOS);


    List<ProductDTO> toDTOList(List<Product> products);

}
