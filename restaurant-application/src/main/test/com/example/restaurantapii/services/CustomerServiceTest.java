package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.Mapper.CustomerMapper;
import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.builder.CustomerDTOBuilder;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Customer;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private MediaMapper mediaMapper;

    private Customer customer = new Customer();
    private CustomerDTO customerDTO = new CustomerDTO();
    private List<Customer> customerList = new ArrayList<>();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    private MediaDTO mediaDTO = new MediaDTO();
    private Media media = new Media();
    @Before
    public void setUp(){
        mediaDTO = new MediaDTOBuilder().name("ASDASD").id(1L).build();
        media.setId(2L);
        when(mediaMapper.toEntity(any())).thenReturn(media);
        customerDTO = new CustomerDTOBuilder().name("asdasd").id(1L).lastName("ASDA").phone(123455L).address("ASDASD").build();
        customerDTO.setMedia(mediaDTO);
        customerDTOList.add(customerDTO);
        customer.setName("abc");
        customer.setAddress("abc");
        customer.setPhone(12345L);
        customer.setLastName("abc");
        customer.setMedia(media);
        customerList.add(customer);
        when(customerMapper.toEntity(any())).thenReturn(customer);
        when(customerMapper.toDTO(any())).thenReturn(customerDTO);
        when(customerMapper.toDTOList(any())).thenReturn(customerDTOList);

    }

    @Test
    public void shouldAddCustomer(){
        when(customerRepository.save(any())).thenReturn(customer);
        CustomerDTO res = customerService.addCustomer(customerDTO);
        assertNotNull(res);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAddCustomerWhenNameNull(){
        customerDTO.setName(null);
        when(customerRepository.save(any())).thenReturn(customer);
        customerService.addCustomer(customerDTO);
    }

    @Test
    public void shouldGetAllCustomers(){
        when(customerRepository.findAll()).thenReturn(customerList);
        List<CustomerDTO> res = customerService.allCustomers();
        assertNotNull(res);
    }

    @Test
    public void shouldDeleteCustomer(){
        Long id=1L;
        when(customerRepository.existsById(any())).thenReturn(Boolean.TRUE);
        customerService.deleteCustomer(id);
        verify(customerRepository).deleteById(id);
    }

    @Test(expected = SystemException.class)
    public void shouldNotDeleteWhenIdNotFound(){
        Long id=1L;
        customerService.deleteCustomer(id);
    }

    @Test
    public void shouldGetCustomerByID(){
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        CustomerDTO res = customerService.getCustomerById(customerDTO.getId());
        assertNotNull(res);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotGetCustomerWhenIdNull(){
        Long id = null;
        customerService.getCustomerById(id);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotGetCustomerWhenCustomerNotFound(){
        Long id =1L;
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        customerDTO.setId(null);
        customerService.getCustomerById(id);
    }

    @Test
    public void shouldUpdateCustomer(){
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);
        CustomerDTO res = customerService.updateCustomer(customerDTO);
        assertNotNull(res);
        assertNotEquals(res.getId(),customer.getId());
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotUpdateWhenContentNotFound(){
        when(customerRepository.findById(any())).thenReturn(Optional.empty());
        customerService.updateCustomer(customerDTO);
    }



}
