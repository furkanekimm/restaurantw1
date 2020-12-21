package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Media;

public class WaiterDTOBuilder extends Builder {
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;
    private MediaDTO mediadto;


    @Override
    public WaiterDTO build() {
        WaiterDTO waiterDTO = new WaiterDTO();
        waiterDTO.setId(super.id);
        waiterDTO.setWaiterName(this.waiterName);
        waiterDTO.setWaiterLastName(this.waiterLastName);
        waiterDTO.setPhoneNumber(this.phoneNumber);
        waiterDTO.setEmail(this.email);
        waiterDTO.setMediadto(this.mediadto);
        return waiterDTO;
    }
    public WaiterDTOBuilder media(MediaDTO media){
        this.mediadto=media;
        return this;
    }

    public WaiterDTOBuilder id(Long id){
        super.id=id;
        return this;
    }

    public WaiterDTOBuilder waiterName(String waiterName) {
        this.waiterName = waiterName;
        return this;
    }

    public WaiterDTOBuilder waiterLastName(String waiterLastName) {
        this.waiterLastName = waiterLastName;
        return this;
    }

    public WaiterDTOBuilder phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public WaiterDTOBuilder email(String email) {
        this.email = email;
        return this;
    }
}
