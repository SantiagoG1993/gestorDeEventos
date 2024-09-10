package com.mindhub.appEventos.repositories;

import com.mindhub.appEventos.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location,Long> {

}
