package com.mindhub.appEventos.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name,img,location;
    private int capacity;
    @OneToMany(mappedBy = "location")
    private Set<EventLocation> eventLocationSet = new HashSet<>();



    public Location() {
    }

    public Location(String name, String img, String location, int capacity) {
        this.name = name;
        this.img = img;
        this.location = location;
        this.capacity = capacity;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<EventLocation> getEventLocationSet() {
        return eventLocationSet;
    }

    public void setEventLocationSet(Set<EventLocation> eventLocationSet) {
        this.eventLocationSet = eventLocationSet;
    }
    public void addEventLocation(EventLocation eventLocation){
        eventLocation.setLocation(this);
        eventLocationSet.add(eventLocation);

    }
}
