package com.example.restaurantapii.converters;

import com.example.restaurantapii.dto.*;
import com.example.restaurantapii.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EntityConvertor {

    private  EntityConvertor(){

    }
    public static WaiterDTO convertToWaiter(Waiter waiter){
        WaiterDTO waiterDTO = new WaiterDTO();
        waiterDTO.setId(waiter.getId());
        waiterDTO.setWaiterName(waiter.getWaiterName());
        waiterDTO.setWaiterLastName(waiter.getWaiterLastName());
        waiterDTO.setPhoneNumber(waiter.getPhoneNumber());
        waiterDTO.setEmail(waiter.getEmail());
        //Değişti
        waiterDTO.setMediadto(new MediaDTO());
        return waiterDTO;
    }

    public static ProductDTO convertToProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setUrlToImage(product.getUrlToImage());
        //productDTO.setCategoryDTOSet(product.getCategory());
        //değişti
        productDTO.setCategoryName(new HashSet<CategoryDTO>());
        productDTO.setMedia(product.getMedia());
        //productDTO.setCategoryId(product.getCategory().getId());*/
        return productDTO;
    }

    public static CategoryDTO convertToCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setName(category.getName());
        categoryDTO.setFileContent(category.getMedia().getFileContent());
        categoryDTO.setMediaId(category.getMedia().getId());
        return categoryDTO;
    }

    public static PlaceRestDTO convertToPlaceRest(PlaceRest placeRest){
        PlaceRestDTO placeRestDTO = new PlaceRestDTO();
        placeRestDTO.setId(placeRest.getId());
        placeRestDTO.setName(placeRest.getName());
        placeRestDTO.setTablePiece(placeRest.getTablePiece());
        return placeRestDTO;
    }

    public static CartDTO convertToCart(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCategoryId(cart.getCategoryId());
        cartDTO.setCreateDate(cart.getCreateDate());
        cartDTO.setPiece(cart.getPiece());
        cartDTO.setProductId(cart.getProductId());
        cartDTO.setTableId(cart.getTableId());
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setWaiterId(cart.getWaiterId());
        return cartDTO;
    }
    public static MediaDTO convertToMediaDTO(Media media){
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setName(media.getName());
        mediaDTO.setFileContent(media.getFileContent());
        return mediaDTO;
    }

    public static UserDTO convertToUser(User user){
        UserDTO userDTO = new UserDTO();
        List<Long> rolesId= new ArrayList<>();
        user.getRoles().forEach(role -> rolesId.add(role.getId()));
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setRolesId(rolesId);
        return userDTO;
    }

    public static RoleDTO convertToRole(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

}
