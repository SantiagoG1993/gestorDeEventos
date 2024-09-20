package com.mindhub.appEventos.controllers;
import com.mindhub.appEventos.dtos.CommentDTO;
import com.mindhub.appEventos.models.Comment;
import com.mindhub.appEventos.models.Customer;
import com.mindhub.appEventos.models.Event;
import com.mindhub.appEventos.repositories.CommentRepository;
import com.mindhub.appEventos.repositories.CustomerRepository;
import com.mindhub.appEventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public List<CommentDTO> getAllComments(){
        return commentRepository.findAll().stream().map(CommentDTO::new).collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<?>createComment(@RequestParam String comment,
                                          @RequestParam Long customer_id,
                                          @RequestParam Long event_id){
        if(comment.isBlank()){
            return new ResponseEntity<>("Please enter comment", HttpStatus.FORBIDDEN);

        }else{
            Comment newComment = new Comment(comment);
            Event event=eventRepository.findById(event_id).orElse(null);
            Customer customer = customerRepository.findById(customer_id).orElse(null);
            customer.addComment(newComment);
            event.addComment(newComment);

            commentRepository.save(newComment);
            customerRepository.save(customer);
            eventRepository.save(event);
            return new ResponseEntity<>(new CommentDTO(newComment),HttpStatus.CREATED);
        }

    }


}
