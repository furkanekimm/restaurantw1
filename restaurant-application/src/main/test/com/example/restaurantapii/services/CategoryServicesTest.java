package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServicesTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryDTO categoryDTO = new CategoryDTO();
    private MediaDTO mediaDTO = new MediaDTO();
    private List<Category> categoryList = new ArrayList<>();
    private Category category = new Category();
    private List<Product> productList = new ArrayList<>();
    private Media media = new Media();

    @Before
    public void setUp() throws Exception{
       mediaDTO.setId(1L);
       media.setId(2L);
       category.setId(1L);
       category.setMedia(media);
       categoryDTO = new CategoryDTOBuilder().id(1L).description("abc").name("new").build();
       categoryDTO.setMedia(mediaDTO);
       mediaDTO = new MediaDTOBuilder().id(1L).name("abc.PNG").build();
       when(categoryMapper.toEntity(any())).thenReturn(category);
       when(categoryMapper.toDTO(any())).thenReturn(categoryDTO);
    }

    @Test
    public void shouldAddCategory(){
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDTO res = categoryService.addCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res.getName(),categoryDTO.getName());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldAddCategoryWhenMediaIdNull(){
        categoryDTO.getMedia().setId(null);
        categoryService.addCategory(categoryDTO);
    }

    @Test
    public void shouldGetProductByID(){
        Long id=1L;
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        CategoryDTO res = categoryService.getCategoryByID(id);
        assertEquals(res.getId(),category.getId());
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotGetProductWhenEmptyRecord(){
        Long id=1L;
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        categoryService.getCategoryByID(id);
    }

    @Test
    public void shouldDeleteCategory(){
        when(categoryRepository.existsById(any())).thenReturn(Boolean.TRUE);
        Long id = 1L;
        Boolean res = categoryService.deleteCategory(id);
        assertEquals(res,true);
    }

    @Test(expected = SystemException.class)
    public void shouldDeleteCategoryWhenNotExist(){
        when(categoryRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Long id = 1L;
        categoryService.deleteCategory(id);
    }

    @Test
    public void shouldListAllCategories(){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        when(categoryMapper.toDTOList(any())).thenReturn(categoryDTOList);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<CategoryDTO> res = categoryService.allCategories();
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateCategory(){
        category.setName("abcef");
        category.setDescription("abcef");
        category.setProducts(productList);
        category.getMedia().setId(1L);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        Boolean res = categoryService.updateCategory(categoryDTO);
        assertNotNull(res);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateCategoryWhenIdNull(){
        categoryDTO.setId(null);
        categoryService.updateCategory(categoryDTO);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotUpdateCategoryWhenNotFound(){
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        categoryService.updateCategory(categoryDTO);
    }

}
