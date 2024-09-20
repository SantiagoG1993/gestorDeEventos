package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.EventDTO;
import com.mindhub.appEventos.models.CreateEvent;
import com.mindhub.appEventos.models.Event;
import com.mindhub.appEventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping
    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CreateEvent createEvent){
        if(createEvent.name().isBlank() || createEvent.desc().isBlank() || createEvent.img().isBlank() || createEvent.age_req() <=0){
            return new ResponseEntity<>("Error creating event ", HttpStatus.BAD_REQUEST);
        }else{
            Event event = new Event(createEvent.desc(), createEvent.img(), createEvent.name(), createEvent.age_req());
            eventRepository.save(event);


            return new ResponseEntity<>(new EventDTO(event),HttpStatus.CREATED);
        }
    }
}
