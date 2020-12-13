package com.example.restaurantapii.converters;

import com.example.restaurantapii.dto.*;
import com.example.restaurantapii.entity.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class DTOConverter {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private DTOConverter(){

    }

    public static Product convertDTOProduct(ProductDTO productDTO){
        Product product= new Product();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setUrlToImage(productDTO.getUrlToImage());
        product.setCategory(new HashSet<>());
        product.setMedia(productDTO.getMedia());
        //product.setCategory(productDTO.getCategoryDTOSet());
       /* product.setCategory(new Set<Category>);
        product.getCategory().setName(productDTO.getCategoryName());
        product.getCategory().setId(productDTO.getCategoryId());*/
        return product;
    }

    public static Waiter convertDTOWaiter(WaiterDTO waiterDTO){
        Waiter waiter = new Waiter();
        waiter.setId(waiterDTO.getId());
        waiter.setWaiterName(waiterDTO.getWaiterName());
        waiter.setWaiterLastName(waiterDTO.getWaiterLastName());
        waiter.setEmail(waiterDTO.getEmail());
        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        waiter.setMedia(waiterDTO.getMedia());
        return waiter;
    }

    public static Category convertDTOCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        category.setName(categoryDTO.getName());
        return  category;
    }

    public static PlaceRest convertDTOPlaceRest(PlaceRestDTO placeRestDTO){
        PlaceRest placeRest = new PlaceRest();
        placeRest.setId(placeRestDTO.getId());
        placeRest.setName(placeRestDTO.getName());
        placeRest.setTablePiece(placeRestDTO.getTablePiece());
        return placeRest;
    }

    public static Cart convertToCartDTO(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setCategoryId(cartDTO.getCategoryId());
        cart.setCreateDate(cartDTO.getCreateDate());
        cart.setPiece(cartDTO.getPiece());
        cart.setProductId(cartDTO.getProductId());
        cart.setTableId(cartDTO.getTableId());
        cart.setTotal(cartDTO.getTotal());
        cart.setWaiterId(cartDTO.getWaiterId());
        return cart;
    }

    public static Media convertToMediaDTO(MediaDTO mediaDTO){
        Media media = new Media();
        media.setId(mediaDTO.getId());
        media.setName(mediaDTO.getName());
        media.setFileContent(mediaDTO.getFileContent());
        return media;
    }

    public static User convertToUserDTO(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        return user;
    }

    public static Role convertToRoleDTO(RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

}
