package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
@Validated
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public List<CartDTO> addCart(@NotEmpty(message="{LIST_IS_EMPTY}") @RequestBody List<CartDTO> cart) {
       return cartService.addCart(cart);
    }

    @GetMapping()
    public List<CartDTO> allCarts(){
        return cartService.allCarts();
    }

}
