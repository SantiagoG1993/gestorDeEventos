package com.mindhub.appEventos.controllers;
import com.mindhub.appEventos.config.JwtUtils;
import com.mindhub.appEventos.dtos.CustomerDTO;
import com.mindhub.appEventos.dtos.creationRequests.CreateUser;
import com.mindhub.appEventos.dtos.creationRequests.LoginRequest;
import com.mindhub.appEventos.models.Customer;
import com.mindhub.appEventos.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());
        Map<String, String> response = new HashMap<>();
        response.put("token",jwt);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUser createUser) {
        Customer customer = new Customer(createUser.name(), createUser.lastName(), createUser.email(), createUser.password(), createUser.age(),true,createUser.gender());
        customerRepository.save(customer);
        return ResponseEntity.ok("User registered successfully");
    }
    @GetMapping("/customer")
    CustomerDTO getAuthClient(Authentication authentication){
        Customer authCustomer = customerRepository.findByEmail(authentication.getName()).orElse(null);
        if(authCustomer!= null){
            return  new CustomerDTO(authCustomer);
        }else{
            return null;
        }
    }
}
