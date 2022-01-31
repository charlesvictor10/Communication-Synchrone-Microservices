package dev.charles.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.charles.dto.CustomerRequestDTO;
import dev.charles.dto.CustomerResponseDTO;
import dev.charles.entities.Customer;
import dev.charles.mappeurs.CustomerMapper;
import dev.charles.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
		Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
		customer.setId(UUID.randomUUID().toString());
		Customer saveCustomer = customerRepository.save(customer);
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(saveCustomer);
		return customerResponseDTO;
	}

	@Override
	public CustomerResponseDTO getOneCustomer(String id) {
		Customer customer = customerRepository.findById(id).get();
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);
		return customerResponseDTO;
	}

	@Override
	public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
		Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
		Customer updateCustomer = customerRepository.save(customer);
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(updateCustomer);
		return customerResponseDTO;
	}

	@Override
	public List<CustomerResponseDTO> listeCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerResponseDTO> customerResponseDTOs = customers.stream()
				.map(cust -> customerMapper.customerToCustomerResponseDTO(cust))
				.collect(Collectors.toList());
		return customerResponseDTOs;
	}
}
