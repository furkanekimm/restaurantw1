package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private CategoryDTO categoryDTO = new CategoryDTO();

    private List<CategoryDTO> categoryDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        categoryDTO = new CategoryDTOBuilder().id(1L).description("abc").name("new").build();
    }

    @Test
    public void shouldAddCategory(){
        when(categoryService.addCategory(any())).thenReturn(categoryDTO);
        CategoryDTO res = categoryController.addCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res,categoryDTO);
    }

    @Test
    public void shouldAllCategory(){
        categoryDTOList.add(categoryDTO);
        when(categoryService.allCategories()).thenReturn(categoryDTOList);
        List<CategoryDTO> res = categoryController.allCategory();
        assertNotNull(res);
        assertEquals(res,categoryDTOList);
    }

    @Test
    public void shouldDeleteCategory(){
        Long id = 1L;
        when(categoryService.deleteCategory(any())).thenReturn(Boolean.TRUE);
        Boolean res = categoryController.deleteCategory(id);
        assertNotNull(res);
        assertEquals(res,true);
    }

    @Test
    public void shouldNotDeleteCategory(){
        Long id = 1L;
        when(categoryService.deleteCategory(any())).thenReturn(Boolean.FALSE);
        Boolean res = categoryController.deleteCategory(id);
        assertNotNull(res);
        assertEquals(res,false);
    }

    @Test
    public void shouldUpdateCategory(){
        when(categoryService.updateCategory(any())).thenReturn(Boolean.TRUE);
        Boolean res = categoryController.updateCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res,true);
    }


    @Test
    public void shouldNotUpdateCategory(){
        when(categoryService.updateCategory(any())).thenReturn(Boolean.FALSE);
        Boolean res = categoryController.updateCategory(categoryDTO);
        assertNotNull(res);
        assertEquals(res,false);
    }

    @Test
    public void shouldGetAllCategories(){
        when(categoryService.allCategories()).thenReturn(categoryDTOList);
        List<CategoryDTO> res = categoryController.allCategory();
        assertNotNull(res);
    }

    @Test
    public void shouldGetCategoryById(){
        when(categoryService.getCategoryByID(any())).thenReturn(categoryDTO);
        CategoryDTO res = categoryController.getCategoryByID(categoryDTO.getId());
        assertNotNull(res);
    }





}
