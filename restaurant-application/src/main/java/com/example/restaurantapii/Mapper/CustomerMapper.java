package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper  {

    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDTO(Customer customer);

    List<Customer> toEntityList(List<CustomerDTO> customerDTOList);

    List<CustomerDTO> toDTOList(List<Customer> customerList);
}
