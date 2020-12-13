package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServicesTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MediaRepository mediaRepository;

    private CategoryDTO categoryDTO = new CategoryDTO();

    private MediaDTO mediaDTO = new MediaDTO();

    private List<Category> categoryList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
       categoryDTO = new CategoryDTOBuilder().id(1L).urlToImage("null").description("abc").name("new").build();
       mediaDTO = new MediaDTOBuilder().id(1L).name("abc.PNG").build();

    }

    @Test
    public void shouldAddCategory(){
        when(mediaRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertToMediaDTO(mediaDTO)));
        when(categoryRepository.save(any())).thenReturn(DTOConverter.convertDTOCategory(categoryDTO));
        CategoryDTO res = categoryService.addCategory(categoryDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldGetProductByID(){
        when(categoryRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOCategory(categoryDTO)));
        List<ProductDTO> res = categoryService.findProductsById(1L);
        assertNotNull(res);
    }

    @Test
    public void shouldNotGetProductByID(){
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        List<ProductDTO> res = categoryService.findProductsById(2L);
        assertEquals(res, Collections.emptyList());
    }

    @Test
    public void shouldDeleteCategory(){

        when(categoryRepository.existsById(any())).thenReturn(Boolean.TRUE);
        Long id = 1L;
        Boolean res = categoryService.deleteCategory(id);
        assertEquals(res,true);
    }

    @Test
    public void shouldNotDeleteCategory(){

        when(categoryRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Long id = 2L;
        Boolean res = categoryService.deleteCategory(id);
        assertEquals(res,false);
    }

    @Test
    public void shouldListAllCategories(){
        categoryList.add(DTOConverter.convertDTOCategory(categoryDTO));
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<CategoryDTO> res = categoryService.allCategories();
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateCategory(){
        when(mediaRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertToMediaDTO(mediaDTO)));
        when(categoryRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertDTOCategory(categoryDTO));
        Boolean res = categoryService.updateCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res,true);
    }

    @Test
    public void shouldNotUpdateCategory(){
        categoryDTO.setId(0L);
        when(mediaRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertToMediaDTO(mediaDTO)));
        when(categoryRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertDTOCategory(categoryDTO));
        Boolean res = categoryService.updateCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res,false);
    }



}
