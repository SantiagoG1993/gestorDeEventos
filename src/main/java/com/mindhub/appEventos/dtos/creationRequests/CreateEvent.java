package com.mindhub.appEventos.dtos.creationRequests;

import java.time.LocalDate;

public record CreateEvent(String name, String desc, String img, int age_req) {
}
