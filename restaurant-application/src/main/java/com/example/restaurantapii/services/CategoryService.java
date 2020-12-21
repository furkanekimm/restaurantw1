package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
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

    public List<ProductDTO> findProductsById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()){
            return Collections.emptyList();
        }
        Set<Product> products = optionalCategory.get().getProducts();
        List<ProductDTO> productDTOSet = new ArrayList<>() ;
        products.forEach(product -> productDTOSet.add(EntityConvertor.convertToProduct(product)));
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
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        Media media =mediaRepository.findById(categoryDTO.getMediaId()).get();
        category.setMedia(media);
        categoryRepository.save(category);
        return categoryDTO;
    }

    public List<CategoryDTO> allCategories(){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.forEach(category -> categoryDTOList.add(CategoryMapper.INSTANCE.toDTO(category)));
        return categoryDTOList;
    }


    public boolean updateCategory(CategoryDTO categoryDTO){
        Category category1 = CategoryMapper.INSTANCE.toEntity(categoryDTO);
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
        categoryDTO = CategoryMapper.INSTANCE.toDTO(categoryRepository.findById(id).get());
        return categoryDTO;
         }
}
