package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
@Validated
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/")
    public List<CategoryDTO> allCategory(){
        return categoryService.allCategories();
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable @NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/update/")
    public boolean updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryByID(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return categoryService.getCategoryByID(id);
    }

}
