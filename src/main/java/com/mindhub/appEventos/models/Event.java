package com.mindhub.appEventos.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc,img,name;
    private int age_req;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer_id")
    private Customer customer;
    @OneToMany(mappedBy = "event")
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "event")
    private Set<EventLocation> eventsLocation= new HashSet<>();

    private Boolean isActive = true;

    public Event() {
    }

    public Event(String desc, String img, String name, int age_req) {
        this.desc = desc;
        this.img = img;
        this.name = name;
        this.age_req = age_req;
    }

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge_req() {
        return age_req;
    }

    public void setAge_req(int age_req) {
        this.age_req = age_req;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public void addComment(Comment comment){
        comment.setEvent(this);
        comments.add(comment);
    }
    public void addEventLocation(EventLocation eventLocation){
        eventsLocation.add(eventLocation);
        eventLocation.setEvent(this);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<EventLocation> getEventsLocation() {
        return eventsLocation;
    }

    public void setEventsLocation(Set<EventLocation> eventsLocation) {
        this.eventsLocation = eventsLocation;
    }
}
