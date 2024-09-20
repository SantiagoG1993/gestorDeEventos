package com.mindhub.appEventos.dtos;

import com.mindhub.appEventos.models.Location;

public class LocationDTO {
    private Long id;
    private String name,img,location;
    private int capacity;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.img = location.getImg();
        this.location = location.getLocation();
        this.capacity = location.getCapacity();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }
}
