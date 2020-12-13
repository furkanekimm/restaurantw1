package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.builder.ProductDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.services.CategoryService;
import com.example.restaurantapii.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    private ProductDTO productDTO = new ProductDTO();

    private CategoryDTO categoryDTO = new CategoryDTO();

    private List<ProductDTO> productDTOList = new ArrayList<>();



    @Before
    public void setUp(){
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        categoryDTO = new CategoryDTOBuilder().id(1L).urlToImage("null").description("abc").name("new").build();
        categoryService.addCategory(categoryDTO);
        productDTO = new ProductDTOBuilder().id(1L).categoryId(longList).productName("new").build();
    }

    @Test
    public void shouldAddProduct(){
        when(productService.addProduct(any())).thenReturn(productDTO);
        ProductDTO res = productController.addProduct(productDTO);
        assertNotNull(res);
        assertEquals(res,productDTO);

    }

    @Test
    public void shouldNotAddProduct(){
        Long id = 2L;
        when(productService.addProduct(any())).thenReturn(productDTO);
        ProductDTO res = productController.addProduct(productDTO);
        assertNotEquals(res.getCategoryId(),id);
    }

    @Test
    public void shouldFindProductsByID(){
        productDTOList.add(productDTO);
        Long id = 1L;
        when(categoryService.findProductsById(any())).thenReturn(productDTOList);
        List<ProductDTO> res = productController.findProductsById(id);
        assertNotNull(res);
        assertEquals(res,productDTOList);
    }

    @Test
    public void shouldListAllProducts(){
        productDTOList.add(productDTO);
        when(productService.listAllProduct()).thenReturn(productDTOList);
        List<ProductDTO> res = productController.listAllProduct();
        assertNotNull(res);
        assertEquals(res,productDTOList);
    }

    @Test
    public void shouldGetProductById(){
        Long id = 1L;
        when(productService.getProductById(any())).thenReturn(productDTO);
        ProductDTO res = productController.getProductById(id);
        assertNotNull(res);
        assertEquals(res,productDTO);

    }

    @Test
    public void shouldNotGetProductById(){
        Long id = 2L;
        when(productService.getProductById(any())).thenReturn(productDTO);
        ProductDTO res = productController.getProductById(id);
        assertNotEquals(res.getId(),id);
    }

    @Test
    public void shouldDeleteProduct(){
        Long id = 1L;
        when(productService.deleteProduct(any())).thenReturn(Boolean.TRUE);
        Boolean res = productController.deleteProduct(id);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeleteProduct(){
        Long id = 1L;
        when(productService.deleteProduct(any())).thenReturn(Boolean.FALSE);
        Boolean res = productController.deleteProduct(id);
        assertEquals(false,res);
    }

    @Test
    public void shouldUpdateProduct(){
        Long id = 1L;
        when(productService.updateProduct(any())).thenReturn(productDTO);
        ProductDTO res = productController.updateProduct(productDTO);
        assertNotNull(res);
        assertEquals(res.getCategoryId().get(0),id);
    }

    @Test
    public void shouldNotUpdateProduct(){
        Long id =2L;
        when(productService.updateProduct(any())).thenReturn(productDTO);
        ProductDTO res = productController.updateProduct(productDTO);
        assertNotEquals(res.getCategoryId(),id);
    }

}
