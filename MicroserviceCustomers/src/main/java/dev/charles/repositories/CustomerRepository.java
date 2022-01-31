package dev.charles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
