package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.builder.CategoryDTOBuilder;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.builder.ProductDTOBuilder;
import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;

import java.net.ContentHandler;
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

    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Spy
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

    private ProductDTO productDTO = new ProductDTO();
    private CategoryDTO categoryDTO = new CategoryDTO();
    private List<ProductDTO> productDTOList = new ArrayList<>();
    private List<Category> list = new ArrayList<>();
    private Product product = new Product();
    private Category category = new Category();
    private ProductDTO productDTO1 = new ProductDTO();
    private MediaDTO mediaDTO = new MediaDTO();
    private Media media = new Media();

    @Before
    public void setUp() throws Exception{
        byte[] myvar = "Any String you want".getBytes();
        mediaDTO = new MediaDTOBuilder().name("faf").id(1L).fileContent(myvar).build();
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        categoryDTO = new CategoryDTOBuilder().id(1L).description("abc").name("new").build();
        category = categoryMapper.toEntity(categoryDTO);
        list.add(category);
        categoryRepository.save(categoryMapper.toEntity(categoryDTO));
        productDTO = new ProductDTOBuilder().id(2L).categoryId(longList).description("sd").price(12L).productName("abv").build();
        productDTO.setMedia(mediaDTO);
        product = productMapper.toEntity(productDTO);
        product.setCategory(list);


    }


    @Test
    public void shouldAddNewProduct(){
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(productRepository.save(any())).thenReturn(product);
        ProductDTO productDTOTest = productService.addProduct(productDTO);
        assertNotNull(productDTOTest);
        assertEquals(productDTOTest,productDTO);
    }

    @Test
    public void shouldDeleteProduct(){
        Long id = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productRepository.existsById(any())).thenReturn(Boolean.TRUE);

        Boolean res = productService.deleteProduct(id);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeleteProduct(){
        Long id = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Boolean res = productService.deleteProduct(id);
        assertEquals(false,res);
    }

    @Test
    public void shouldListAllProduct(){
        List<Product> productList = new ArrayList<>();
        productDTOList.add(productDTO);
        productDTOList.forEach(productDTO1 -> productList.add(product));
        when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> productDTOList1 = productService.listAllProduct();
        assertNotNull(productDTOList1);
       // assertEquals(productDTOList1,productDTOList);

    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldUpdateProduct(){
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(23L);
        productDTO = new ProductDTOBuilder().id(12L).productName("aaa").description("bbb").price(1123124L).build();
        productDTO.setMedia(mediaDTO);
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(productRepository.saveAndFlush(any())).thenReturn(product);
        ProductDTO productDTOTest = productService.updateProduct(productDTO);
        assertNotNull(productDTOTest);
        assertEquals(productDTOTest,productDTO);
    }

    @Test
    public void shouldGetProductByID(){
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        ProductDTO res = productService.getProductById(1L);
        assertNotNull(res);
        //assertEquals(res,productDTO);

    }




}
