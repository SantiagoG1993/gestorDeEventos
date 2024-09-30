package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.EventDTO;
import com.mindhub.appEventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guest")
public class GuestController {


    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }



}
