package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    
    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/")
    public List<CategoryDTO> allCategory(){
        return categoryService.allCategories();
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/update/")
    public boolean updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryByID(@PathVariable Long id){
        return categoryService.getCategoryByID(id);
    }

}
