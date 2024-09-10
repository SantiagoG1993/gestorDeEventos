package com.mindhub.appEventos.repositories;

import com.mindhub.appEventos.models.CustomerEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEventRepository extends JpaRepository<CustomerEvent,Long> {
}
