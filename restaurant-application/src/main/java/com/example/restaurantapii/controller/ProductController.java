package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.services.CategoryService;
import com.example.restaurantapii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }
    @GetMapping("/category/{id}")
    public List<ProductDTO> findProductsById(@PathVariable Long id){
        return categoryService.findProductsById(id);
    }

    @GetMapping("/")
    public List<ProductDTO> listAllProduct() {
        return productService.listAllProduct();
    }


    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
       return productService.getProductById(id);
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }



}
