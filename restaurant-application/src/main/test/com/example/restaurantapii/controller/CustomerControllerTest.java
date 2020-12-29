package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.builder.CustomerDTOBuilder;
import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerDTO customerDTO = new CustomerDTO();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        customerDTO = new CustomerDTOBuilder().name("ABC").lastName("asd").id(1L).phone(1234L).address("aasdasd").build();
        customerDTOList.add(customerDTO);
    }

    @Test
    public void shouldAddCustomer(){
        when(customerService.addCustomer(any())).thenReturn(customerDTO);
        CustomerDTO res = customerController.addCustomer(customerDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldDeleteCustomer(){
        Long id = 1L;
        when(customerService.deleteCustomer(any())).thenReturn(Boolean.TRUE);
        Boolean res = customerController.deleteCustomer(id);
        assertNotNull(res);
    }

    @Test
    public void shouldGetAllCustomers(){
        when(customerService.allCustomers()).thenReturn(customerDTOList);
        List<CustomerDTO> res = customerController.allCustomers();
        assertNotNull(res);
    }

    @Test
    public void shouldUpdataCustomer(){
        when(customerService.updateCustomer(any())).thenReturn(customerDTO);
        CustomerDTO res = customerController.updateCustomer(customerDTO);
        assertNotNull(res);
    }



}
