package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product =productMapper.toEntity(productDTO);
        List<Long> categoryIds = productDTO.getCategoryId();

        if(categoryIds.isEmpty()){
           return null;
        }

        prepareCategoryProduct(product, categoryIds);
        productRepository.save(product);
        return productDTO;
    }

    public List<ProductDTO> listAllProduct() {
        return productMapper.toDTOList(productRepository.findAll());
    }


    public ProductDTO getProductById(Long id){
        Optional<Product> optionalProduct = getProductAndControl(id);
        Product product = optionalProduct.get();
        ProductDTO productDTO = productMapper.toDTO(product);
        List<Long> categories= new ArrayList<>();
        product.getCategory().forEach(category -> categories.add(category.getId()));

        if(categories.isEmpty()){
            throw new BusinessRuleException(Errors.RECORD_NOT_FOUND);
        }

        productDTO.setCategoryId(categories);
        return productDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = getProductAndControl(id);
        Product product = optionalProduct.get();
        product.getCategory().forEach(category -> category.removeProduct(product));
        productRepository.deleteById(id);
        return  true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if(productDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());

        if(optionalProduct.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        Product product = optionalProduct.get();
        fieldControlAndUpdate(productDTO, product);
        productRepository.save(product);
        return productDTO;
    }

    public Page<ProductDTO> getProductLikePage(Pageable pageable){
        Page<Product> productPages = productRepository.findAll(pageable);
        if(productPages==null){
            return  new PageImpl(null,pageable,productPages.getTotalElements());
        }
        List<ProductDTO> dtoList = productMapper.toDTOList(productPages.getContent());
        Page<ProductDTO> pages = new PageImpl(dtoList,pageable,productPages.getTotalElements());
        return pages;
   }

   public Slice<ProductDTO> getProductWithSlice(Pageable pageable,Long id){
        Category category = categoryRepository.findById(id).get();
        Slice<Product> productPage = productRepository.findProductsByCategory(category,pageable);
        List<ProductDTO> dtoList = productMapper.toDTOList(productPage.getContent());
        Slice<ProductDTO> dtoSlice = new PageImpl(dtoList,pageable,productPage.getSize());
        return dtoSlice;
   }

    private void prepareCategoryProduct(Product product, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        if(categories==null){
            return;
        }
        categories.forEach(category -> category.getProducts().add(product));
    }


    private Optional<Product> getProductAndControl(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }
        return optionalProduct;
    }

    private void fieldControlAndUpdate(ProductDTO productDTO, Product product) {
        if(!productDTO.getProductName().equals(product.getProductName())){
            product.setProductName(productDTO.getProductName());
        }
        if(!product.getDescription().equals(productDTO.getDescription())){
            product.setDescription(productDTO.getDescription());
        }
        if(!product.getPrice().equals(productDTO.getPrice())){
            product.setPrice(productDTO.getPrice());
        }
        if(!product.getMedia().getId().equals(productDTO.getMedia().getId())){
            product.setMedia(mediaMapper.toEntity(productDTO.getMedia()));
        }

        List<Category> categories = categoryRepository.findAllById(productDTO.getCategoryId());
        if(categories.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        product.getCategory().forEach(category -> product.getCategory().remove(category));
        product.setCategory(categories);
    }
}
