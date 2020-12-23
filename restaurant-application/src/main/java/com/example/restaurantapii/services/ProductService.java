package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CategoryMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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


    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product =productMapper.toEntity(productDTO);
        List<Long> categoryIds = productDTO.getCategoryIds();

        if(categoryIds.isEmpty()){
           return null;
        }

        prepareCategoryProduct(product, categoryIds);
        productRepository.save(product);
        return productDTO;
    }

    private void prepareCategoryProduct(Product product, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        if(categories==null){
            return;
        }
        categories.forEach(category -> category.getProducts().add(product));
    }

    public List<ProductDTO> listAllProduct() {
        return productMapper.toDTOList(productRepository.findAll());
    }

    public ProductDTO getProductById(Long id){
        if(id==null){
            return null;
        }

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()){
            return null;
        }

        Product product = optionalProduct.get();
        ProductDTO productDTO = productMapper.toDTO(product);
        List<Long> categories= new ArrayList<>();
        product.getCategories().forEach(category -> categories.add(category.getId()));
        productDTO.setCategoryIds(categories);
        return productDTO;
    }

    public Boolean deleteProduct(Long id) {
        if(id==null){
            return null;
        }

        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()){
            return false;
        }

        Product product = optionalProduct.get();
        product.getCategories().forEach(category -> category.removeProduct(product));
        productRepository.deleteById(id);

        return  true;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        if(productDTO==null || productDTO.getId()==null){
            return null;
        }

        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());

        if(!optionalProduct.isPresent()){
            return null;
        }

        Product product = optionalProduct.get();
        product.setMedia(mediaMapper.toEntity(productDTO.getMedia()));

        if(!productDTO.getProductName().equals(product.getProductName())){
            product.setProductName(productDTO.getProductName());
        }

        List<Category> categories = categoryRepository.findAllById(productDTO.getCategoryIds());
        product.getCategories().forEach(category -> product.getCategories().remove(category));
        product.setCategories(categories);
        /*if(categories.isEmpty()){
            product.setCategories(categoryMapper.toEntityList(productDTO.getCategory()));
        }else{

            Set<Category> categorySet = new HashSet<>();
            product.getCategories().forEach(category -> {
                productDTO.getCategory().forEach(categoryDTO -> {
                    if(categoryDTO.getId()==category.getId()){
                        categorySet.add(category);
                    }
                });
            } );

        }
*/
        productRepository.saveAndFlush(product);
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
        Slice<Product> productPage = productRepository.findProductsByCategories(category,pageable);
        List<ProductDTO> dtoList = productMapper.toDTOList(productPage.getContent());
        //Slice<ProductDto> productByProductcategoryId = productRepository.findProductByProductcategoryId(ProductCategoryId, pages).map(productMapper::toDto);
        Slice<ProductDTO> dtoSlice = new PageImpl(dtoList,pageable,productPage.getSize());
        return dtoSlice;
   }


}