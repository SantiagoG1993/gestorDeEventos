package com.mindhub.appEventos.controllers;

import com.mindhub.appEventos.dtos.CommentDTO;
import com.mindhub.appEventos.dtos.EventDTO;
import com.mindhub.appEventos.dtos.creationRequests.CreateEvent;
import com.mindhub.appEventos.models.Event;
import com.mindhub.appEventos.repositories.CommentRepository;
import com.mindhub.appEventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    CommentRepository commentRepository;


    @GetMapping("/events")
    public List<EventDTO> getAllEvents(){
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody CreateEvent createEvent){
        if(createEvent.name().isBlank() || createEvent.desc().isBlank() || createEvent.img().isBlank() || createEvent.age_req() <=0){
            return new ResponseEntity<>("Error creating event ", HttpStatus.BAD_REQUEST);
        }else{
            Event event = new Event(createEvent.desc(), createEvent.img(), createEvent.name(), createEvent.age_req());
            eventRepository.save(event);
            return new ResponseEntity<>(new EventDTO(event),HttpStatus.CREATED);
        }
    }

    @PostMapping("/cancelEvent")
    public ResponseEntity<?> cancelEvent(@RequestParam Long event_id) {
        if (event_id != null) {
            Event event = eventRepository.findById(event_id).orElse(null);
            if (event != null) {
                event.setActive(false);
                eventRepository.save(event);
                return new ResponseEntity<>("Evento " + event.getName() + " cancelado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Error al cancelar el evento: ID no proporcionado", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<CommentDTO> getAllComments(){
        return commentRepository.findAll().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

}
