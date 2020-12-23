package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.ProductMapper;
import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
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


    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product =productMapper.toEntity(productDTO);
        for(int i=0; i<productDTO.getCategoryId().size();i++){
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.save(product);
        return productDTO;
    }

    public List<ProductDTO> listAllProduct() {
        List<ProductDTO> productDTOList = productMapper.toDTOList(productRepository.findAll());
        return productDTOList;
    }

    public ProductDTO getProductById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        ProductDTO productDTO = productMapper.toDTO(product);
        List<Long> categories= new ArrayList<>();
        optionalProduct.get().getCategory().forEach(category -> categories.add(category.getId()));
        productDTO.setCategoryId(categories);
        return productDTO;
    }

    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.get().getCategory().forEach(category -> category.getProducts().remove(optionalProduct.get()));
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return  true;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        optionalProduct.get().getCategory().forEach(category -> category.getProducts().remove(optionalProduct.get()));
        Product product =productMapper.toEntity(productDTO);
        List<Category> categoryList = new ArrayList<>();
        for(int i=0; i<productDTO.getCategoryId().size(); i++){
            categoryList.add(categoryRepository.findById(productDTO.getCategoryId().get(i)).get());
        }
        product.setCategory(categoryList);
        product.setMedia(mediaMapper.toEntity(productDTO.getMedia()));
        categoryList.forEach(category -> categoryRepository.saveAndFlush(category));
        for(int i=0; i<productDTO.getCategoryId().size();i++){
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId().get(i));
            category.get().getProducts().add(product);
        }
        productRepository.saveAndFlush(product);
        return productDTO;
    }

    public Page<ProductDTO> getProductLikePage(Pageable pageable){
        Page<Product> productPages = productRepository.findAll(pageable);
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

/*   public List<Product> getProductByCategory(Long id){
        Category category = categoryRepository.findById(id).get();
        return productRepository.findProductsByCategory(category);
   }*/

}