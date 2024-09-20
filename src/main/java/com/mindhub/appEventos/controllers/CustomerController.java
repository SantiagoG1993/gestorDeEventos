package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.CustomerDTO;
import com.mindhub.appEventos.models.CreateUser;
import com.mindhub.appEventos.models.Customer;
import com.mindhub.appEventos.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
     return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateUser createUser){
        if(createUser.age() <=0 || createUser.email().isBlank() || createUser.name().isBlank() || createUser.lastName().isBlank() || createUser.gender() ==null){
            return new ResponseEntity<>("Error creating customer", HttpStatus.BAD_REQUEST);
        }else{
            Customer customer = new Customer(createUser.name(), createUser.lastName(), createUser.email(), createUser.password(), createUser.age(),true,createUser.gender());

            customerRepository.save(customer);

            return new ResponseEntity<>(new CustomerDTO(customer),HttpStatus.CREATED);

        }

    }
}
