package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CustomerMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.entity.Customer;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MediaMapper mediaMapper;

    public CustomerDTO addCustomer(CustomerDTO customerDTO){
        if(customerDTO.getName()==null){
            throw new BusinessRuleException(Errors.RECORD_SHOULD_GET_NAME);
        }

        customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerDTO;
    }

    public List<CustomerDTO> allCustomers(){
        return customerMapper.toDTOList(customerRepository.findAll());
    }

    public Boolean deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new SystemException(Errors.ID_NOT_FOUND);
        }

        customerRepository.deleteById(id);
        return true;
    }

    public CustomerDTO getCustomerById(Long id){
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        CustomerDTO customerDTO = customerMapper.toDTO(customerRepository.findById(id).get());
        categoryNotFoundExceptionControl(customerDTO);
        return customerDTO;
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        categoryNotFoundExceptionControl(customerDTO);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());

        if(!optionalCustomer.isPresent()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();
        controlCustomerField(customerDTO, customer);
        customerRepository.save(customer);
        return customerDTO;
    }

    public Page<CustomerDTO> listCustomersWithPage(Pageable pageable){
        Page<CustomerDTO>  customerDTOPage = customerRepository.findAll(pageable).map(customerMapper::toDTO);
        return customerDTOPage;
    }

    public Page<CustomerDTO> listCustomerByNameWithPage(String name,Pageable pageable){
        Page<CustomerDTO>  customerDTOPage = customerRepository.findCustomerByNameContains(name,pageable).map(customerMapper::toDTO);
        return customerDTOPage;
    }

    private void controlCustomerField(CustomerDTO customerDTO, Customer customer) {
        if(!customer.getName().equals(customerDTO.getName())){
            customer.setName(customerDTO.getName());
        }

        if(!customer.getLastName().equals(customerDTO.getLastName())){
            customer.setLastName(customerDTO.getLastName());
        }

        if(!customer.getAddress().equals(customerDTO.getAddress())){
            customer.setAddress(customerDTO.getAddress());
        }

        if(!customer.getPhone().equals(customerDTO.getPhone())){
            customer.setPhone(customerDTO.getPhone());
        }
        if(!customer.getMedia().getId().equals(customerDTO.getMedia().getId())){
            customer.setMedia(mediaMapper.toEntity(customerDTO.getMedia()));
        }
    }

    private void categoryNotFoundExceptionControl(CustomerDTO customerDTO) {
        if (customerDTO == null || customerDTO.getId() == null) {
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }
    }

}
