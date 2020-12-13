package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public List<CartDTO> addCart(@RequestBody List<CartDTO> cart) {
       return cartService.addCart(cart);
    }

    @GetMapping("/list")
    public List<CartDTO> allCarts(){
        return cartService.allCarts();
    }


}
