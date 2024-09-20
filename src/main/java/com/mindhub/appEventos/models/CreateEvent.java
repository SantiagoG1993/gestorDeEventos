package com.mindhub.appEventos.models;

import java.time.LocalDate;

public record CreateEvent(String name, String desc, String img, int age_req) {
}
