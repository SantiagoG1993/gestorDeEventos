package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.LocationDTO;
import com.mindhub.appEventos.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;


    @GetMapping
    public List<LocationDTO> getAllLocations(){
        return locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }
}
