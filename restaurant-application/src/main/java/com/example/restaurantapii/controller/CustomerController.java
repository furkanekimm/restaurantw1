package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@Validated
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerDTO addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    public  Boolean deleteCustomer(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping
    public List<CustomerDTO> allCustomers(){
        return customerService.allCustomers();
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(customerDTO);
    }

    @GetMapping("/page")
    public Page<CustomerDTO> listCustomersWithPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.listCustomersWithPage(pageable);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/page-name")
    public Page<CustomerDTO> listCustomersByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,@RequestParam String name){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.listCustomerByNameWithPage(name,pageable);
    }

}
