package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> findProductsById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()){
            return Collections.emptyList();
        }
        List<Product> products = optionalCategory.get().getProducts();
        //Set<Product> products = optionalCategory.get().getProducts();
        List<ProductDTO> productDTOSet = productMapper.toDTOList(products);
       // categoryMapper.toDTOList(categoryRepository.findAll());
        return productDTOSet;
    }

    public Boolean deleteCategory(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        Category category = categoryMapper.toEntity(categoryDTO);
        Media media =mediaRepository.findById(categoryDTO.getMediaId()).get();
        category.setMedia(media);
        categoryRepository.save(category);
        return categoryDTO;
    }

    public List<CategoryDTO> allCategories(){
        List<CategoryDTO> categoryDTOList = categoryMapper.toDTOList(categoryRepository.findAll());
        return categoryDTOList;
    }


    public boolean updateCategory(CategoryDTO categoryDTO){
        Category category1 = categoryMapper.toEntity(categoryDTO);
        Media media = mediaRepository.findById(categoryDTO.getMediaId()).get();
        category1.setMedia(media);
        Category category = categoryRepository.saveAndFlush(category1);
        if(category.getId()==0){
            return false;
        }
        return true;
    }

        public CategoryDTO getCategoryByID(Long id){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO = categoryMapper.toDTO(categoryRepository.findById(id).get());
        return categoryDTO;
         }
}
