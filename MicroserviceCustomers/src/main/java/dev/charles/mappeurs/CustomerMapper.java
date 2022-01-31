package dev.charles.mappeurs;

import org.mapstruct.Mapper;

import dev.charles.dto.CustomerRequestDTO;
import dev.charles.dto.CustomerResponseDTO;
import dev.charles.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
	Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);	
}
