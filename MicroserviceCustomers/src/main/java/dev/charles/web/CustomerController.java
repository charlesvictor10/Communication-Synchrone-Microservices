package dev.charles.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.charles.dto.CustomerRequestDTO;
import dev.charles.dto.CustomerResponseDTO;
import dev.charles.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping(path = "/customers")
	public List<CustomerResponseDTO> AllCustomers() {
		return customerService.listeCustomers();
	}
	
	@PostMapping(path = "/customer")
	public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
		return customerService.save(customerRequestDTO);
	}
	
	@GetMapping(path = "/customer/{id}")
	public CustomerResponseDTO getCustomer(@PathVariable String id) {
		return customerService.getOneCustomer(id);
	}
}
