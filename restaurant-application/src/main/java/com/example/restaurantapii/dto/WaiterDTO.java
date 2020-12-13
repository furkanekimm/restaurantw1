package com.example.restaurantapii.dto;

import com.example.restaurantapii.entity.Media;

public class WaiterDTO {

    private Long id;
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;
    private Media media;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getWaiterLastName() {
        return waiterLastName;
    }

    public void setWaiterLastName(String waiterLastName) {
        this.waiterLastName = waiterLastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
