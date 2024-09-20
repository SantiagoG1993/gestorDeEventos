package com.mindhub.appEventos.dtos;

import com.mindhub.appEventos.models.Customer;
import com.mindhub.appEventos.models.Event;

public class EventDTO {
    private Long id;
    private String desc,img,name;
    private int age_req;
    private CustomerDTO customer;

    public EventDTO() {
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.desc = event.getDesc();
        this.img = event.getImg();
        this.name = event.getDesc();
        this.age_req = event.getAge_req();
    }

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getAge_req() {
        return age_req;
    }

}
