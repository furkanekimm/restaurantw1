package com.example.restaurantapii.services;

import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.builder.ProductDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
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
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductDTO productDTO = new ProductDTO();

    private CategoryDTO categoryDTO = new CategoryDTO();

    private List<ProductDTO> productDTOList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        categoryDTO = new CategoryDTOBuilder().id(1L).urlToImage("null").description("abc").name("new").build();
        categoryRepository.save(DTOConverter.convertDTOCategory(categoryDTO));
        productDTO = new ProductDTOBuilder().id(1L).categoryId(longList).productName("new").build();

    }


    @Test
    public void shouldAddNewProduct(){
        when(categoryRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOCategory(categoryDTO)));
        when(productRepository.save(any())).thenReturn(DTOConverter.convertDTOProduct(productDTO));
        ProductDTO productDTOTest = productService.addProduct(productDTO);
        assertNotNull(productDTOTest);
        assertEquals(productDTOTest,productDTO);
    }

    @Test
    public void shouldDeleteProduct(){
        Long id = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOProduct(productDTO)));
        when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);

        Boolean res = productService.deleteProduct(id);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeleteProduct(){
        Long id = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOProduct(productDTO)));
        when(productRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Boolean res = productService.deleteProduct(id);
        assertEquals(false,res);
    }

    @Test
    public void shouldListAllProduct(){
        List<Product> productList = new ArrayList<>();
        productDTOList.add(productDTO);
        productDTOList.forEach(productDTO1 -> productList.add(DTOConverter.convertDTOProduct(productDTO1)));
        when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> productDTOList1 = productService.listAllProduct();
        assertNotNull(productDTOList1);
       // assertEquals(productDTOList1,productDTOList);

    }
    @Test
    public void shouldUpdateProduct(){
        when(productRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOProduct(productDTO)));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOCategory(categoryDTO)));
        when(productRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertDTOProduct(productDTO));
        ProductDTO productDTOTest = productService.updateProduct(productDTO);
        assertNotNull(productDTOTest);
        assertEquals(productDTOTest,productDTO);
    }

    @Test
    public void shouldGetProductByID(){
        when(productRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOProduct(productDTO)));
        ProductDTO res = productService.getProductById(1L);
        assertNotNull(res);
        //assertEquals(res,productDTO);

    }




}
