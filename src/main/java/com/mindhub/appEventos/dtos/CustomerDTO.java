package com.mindhub.appEventos.dtos;

import com.mindhub.appEventos.models.Customer;
import com.mindhub.appEventos.models.Event;
import com.mindhub.appEventos.models.Gender;
import com.mindhub.appEventos.models.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerDTO {
    private Long id;
    private String name,lastName,email;
    private int age;
    private Role role;
    private boolean activated;
    private Gender gender;
    private Set<EventDTO> events;


    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.age = customer.getAge();
        this.role = customer.getRole();
        this.activated = customer.isActivated();
        this.gender = customer.getGender();
        this.events = customer.getEvents().stream().map(EventDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActivated() {
        return activated;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }
}
