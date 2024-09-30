package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.CustomerDTO;
import com.mindhub.appEventos.dtos.EventDTO;
import com.mindhub.appEventos.dtos.LocationDTO;
import com.mindhub.appEventos.models.*;
import com.mindhub.appEventos.models.creationRequests.CreateEvent;
import com.mindhub.appEventos.models.creationRequests.CreateLocation;
import com.mindhub.appEventos.models.creationRequests.CreateUser;
import com.mindhub.appEventos.repositories.CustomerRepository;
import com.mindhub.appEventos.repositories.EventRepository;
import com.mindhub.appEventos.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/events")
    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }
    @PostMapping("/create/event")
    public ResponseEntity<?> createEvent(@RequestBody CreateEvent createEvent){
        if(createEvent.name().isBlank() || createEvent.desc().isBlank() || createEvent.img().isBlank() || createEvent.age_req() <=0){
            return new ResponseEntity<>("Error creating event ", HttpStatus.BAD_REQUEST);
        }else{
            Event event = new Event(createEvent.desc(), createEvent.img(), createEvent.name(), createEvent.age_req());
            eventRepository.save(event);
            return new ResponseEntity<>(new EventDTO(event),HttpStatus.CREATED);
        }
    }
    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/create/customer")
    public ResponseEntity<?> createCustomer(@RequestBody CreateUser createUser){
        if(createUser.age() <=0 || createUser.email().isBlank() || createUser.name().isBlank() || createUser.lastName().isBlank() || createUser.gender() ==null){
            return new ResponseEntity<>("Error creating customer", HttpStatus.BAD_REQUEST);
        }else{
            Customer customer = new Customer(createUser.name(), createUser.lastName(), createUser.email(), createUser.password(), createUser.age(),true,createUser.gender());
            customerRepository.save(customer);
            return new ResponseEntity<>(new CustomerDTO(customer),HttpStatus.CREATED);
        }
    }
    @GetMapping("/locations")
    public List<LocationDTO> getAllLocations(){
        return locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }
    @PostMapping("/create/location")
    public ResponseEntity<?> createLocation(@RequestBody CreateLocation createLocation){
        if(createLocation.capacity() <=0)
            return new ResponseEntity<>("Please enter a valid capacity", HttpStatus.FORBIDDEN);
        if(createLocation.img().isBlank())
            return new ResponseEntity<>("Please enter an image", HttpStatus.FORBIDDEN);
        if(createLocation.name().isBlank())
            return new ResponseEntity<>("Please enter a name",HttpStatus.FORBIDDEN);
        else{
            Location location = new Location(createLocation.name(),createLocation.img(),createLocation.capacity());
            locationRepository.save(location);
            return new ResponseEntity<>(new LocationDTO(location),HttpStatus.CREATED);
        }
    }

}
