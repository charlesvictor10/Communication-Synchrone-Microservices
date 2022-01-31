package dev.charles.service;

import java.util.List;

import dev.charles.dto.CustomerRequestDTO;
import dev.charles.dto.CustomerResponseDTO;

public interface CustomerService {
	CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
	CustomerResponseDTO getOneCustomer(String id);
	CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
	List<CustomerResponseDTO> listeCustomers();
}
