package com.mindhub.appEventos.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EventLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int assistance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToMany(mappedBy = "eventLocation")
    private Set<CustomerEvent> customerEvents = new HashSet<>();


    public EventLocation() {
    }

    public EventLocation(LocalDate date, int assistance) {
        this.date = date;
        this.assistance = assistance;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAssistance() {
        return assistance;
    }

    public void setAssistance(int assistance) {
        this.assistance = assistance;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<CustomerEvent> getCustomerEvents() {
        return customerEvents;
    }

    public void setCustomerEvents(Set<CustomerEvent> customerEvents) {
        this.customerEvents = customerEvents;
    }
    public void addCustomerEvent(CustomerEvent customerEvent){
        customerEvent.setEventLocation(this);
        customerEvents.add(customerEvent);
    }
}
