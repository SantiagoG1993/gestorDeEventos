package com.mindhub.appEventos.repositories;

import com.mindhub.appEventos.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Optional<Customer> findByEmail(String email);
}
