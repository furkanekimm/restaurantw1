package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.CustomerDTO;

public class CustomerDTOBuilder extends Builder {
    private String name;
    private String lastName;
    private String address;
    private Long phone;

    @Override
    public CustomerDTO build() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(super.id);
        customerDTO.setName(this.name);
        customerDTO.setLastName(this.lastName);
        customerDTO.setPhone(this.phone);
        customerDTO.setAddress(this.address);
        return customerDTO;
    }

    public CustomerDTOBuilder id(Long id){
        super.id= id;
        return this;
    }

    public CustomerDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CustomerDTOBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDTOBuilder address(String address) {
        this.address = address;
        return this;
    }

    public CustomerDTOBuilder phone(Long phone) {
        this.phone = phone;
        return this;
    }
}
