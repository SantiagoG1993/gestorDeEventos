package com.mindhub.appEventos.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name,lastName,email,password;
    private int age;
    private boolean activated;
    private Gender gender;
    @OneToMany(mappedBy = "customer")
    private Set<Event> events = new HashSet<>();
    @OneToMany(mappedBy = "customer")
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "customer")
    private Set<CustomerEvent> customerEvents = new HashSet<>();
    public Customer() {
    }

    public Customer(String name, String lastName, String email, String password, int age, boolean activated, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.activated = activated;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
    public void addEvent(Event event){
        events.add(event);
        event.setCustomer(this);
    }
    public void addComment(Comment comment){
        comments.add(comment);
        comment.setCustomer(this);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<CustomerEvent> getCustomerEvents() {
        return customerEvents;
    }

    public void setCustomerEvents(Set<CustomerEvent> customerEvents) {
        this.customerEvents = customerEvents;
    }
    public void addCustomerEvent(CustomerEvent customerEvent){
    customerEvent.setCustomer(this);
    customerEvents.add(customerEvent);
    }
}
