package com.mindhub.appEventos.dtos.creationRequests;

import com.mindhub.appEventos.models.Gender;

public record CreateUser(String name, String lastName, String email, String password, int age, Gender gender) {
}
