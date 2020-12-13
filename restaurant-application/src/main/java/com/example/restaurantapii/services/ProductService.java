package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product =DTOConverter.convertDTOProduct(productDTO);
        for(int i=0; i<productDTO.getCategoryId().size();i++){
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.save(product);
        return productDTO;
    }

    public List<ProductDTO> listAllProduct() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> productDTOList.add(EntityConvertor.convertToProduct(product)));
        return productDTOList;
    }

    public ProductDTO getProductById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        ProductDTO productDTO = EntityConvertor.convertToProduct(product);
        List<Long> categories= new ArrayList<>();
        optionalProduct.get().getCategory().forEach(category -> categories.add(category.getId()));
        productDTO.setCategoryId(categories);
        return productDTO;
    }

    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.get().getCategory().forEach(category -> category.getProducts().remove(optionalProduct.get()));
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return  true;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        optionalProduct.get().getCategory().forEach(category -> category.getProducts().remove(optionalProduct.get()));
        Product product =DTOConverter.convertDTOProduct(productDTO);
        Set<Category> categoryList = new HashSet<>();
        for(int i=0; i<productDTO.getCategoryId().size(); i++){
            categoryList.add(categoryRepository.findById(productDTO.getCategoryId().get(i)).get());
        }
        product.setCategory(categoryList);
        product.setMedia(productDTO.getMedia());
        categoryList.forEach(category -> categoryRepository.saveAndFlush(category));
        for(int i=0; i<productDTO.getCategoryId().size();i++){
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.saveAndFlush(product);
        return productDTO;
    }


}