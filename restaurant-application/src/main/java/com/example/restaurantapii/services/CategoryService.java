package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "categoryData",allEntries = true)
    public Boolean deleteCategory(Long id){
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }
        if(!categoryRepository.existsById(id)){
            throw new SystemException(Errors.ID_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "categoryData",allEntries = true)
    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        if(categoryDTO.getMedia().getId()==null){
            throw new BusinessRuleException(Errors.MEDIA_NOT_FOUND);
        }

        if(categoryDTO.getName()==null){
            throw new BusinessRuleException(Errors.RECORD_SHOULD_GET_NAME);
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        categoryRepository.save(category);
        return categoryDTO;
    }

    @Cacheable(value = "categoryData")
    public List<CategoryDTO> allCategories(){
        List<CategoryDTO> categoryDTOList = categoryMapper.toDTOList(categoryRepository.findAll());
        return categoryDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "categoryData",allEntries = true)
    public boolean updateCategory(CategoryDTO categoryDTO){
        if(categoryDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());

        if(optionalCategory.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        if(!optionalCategory.get().getName().equals(categoryDTO.getName())){
            optionalCategory.get().setName(categoryDTO.getName());
        }
        if(!optionalCategory.get().getDescription().equals(categoryDTO.getDescription())){
            optionalCategory.get().setDescription(categoryDTO.getDescription());
        }
        if(!optionalCategory.get().getMedia().getId().equals(categoryDTO.getMedia().getId())){
            optionalCategory.get().setMedia(mediaMapper.toEntity(categoryDTO.getMedia()));
        }

        categoryRepository.save(optionalCategory.get());
        return true;
    }

    public CategoryDTO getCategoryByID(Long id){
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        return categoryMapper.toDTO(optionalCategory.get());
    }
}
