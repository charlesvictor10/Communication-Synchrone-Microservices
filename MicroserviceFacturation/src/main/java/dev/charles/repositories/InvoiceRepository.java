package dev.charles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	List<Invoice> findByCustomerId(String customerId);
}
