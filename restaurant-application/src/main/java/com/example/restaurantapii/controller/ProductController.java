package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.ProductDTO;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.services.CategoryService;
import com.example.restaurantapii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
@Validated
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @PostMapping()
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @GetMapping()
    public List<ProductDTO> listAllProduct() {
        return productService.listAllProduct();
    }

    @GetMapping("/get/{id}")
    public ProductDTO getProductById(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id) {
       return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @GetMapping("/get-page")
    public Page<ProductDTO> getProductPage(@RequestParam int page){
        int size = 5;
        Pageable pageable = PageRequest.of(page,size);
        return productService.getProductLikePage(pageable);
    }

    @GetMapping("/get-page-slice")
    public Slice<ProductDTO> getProductWithSlice(@RequestParam int page,@RequestParam Long id){
        int size = 3;
        Pageable pageable = PageRequest.of(page,size);
        return productService.getProductWithSlice(pageable,id);
    }


}
