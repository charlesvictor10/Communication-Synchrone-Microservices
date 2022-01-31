package dev.charles.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.charles.dto.InvoiceRequestDTO;
import dev.charles.dto.InvoiceResponseDTO;
import dev.charles.service.InvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api")
public class InvoiceController {
	private InvoiceService invoiceService;

	public InvoiceController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}
	
	@GetMapping(path = "/invoice/{id}")
	public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String invoiceId) {
		return invoiceService.getOneInvoice(invoiceId);
	}
	
	@GetMapping(path = "/invoiceByCustomer/{customerId}")
	public List<InvoiceResponseDTO> getInvoicesByCustomer(@PathVariable String customerId) {
		return invoiceService.invoicesByCustomerId(customerId);
	}
	
	@PostMapping(path = "/invoice")
	public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
		return invoiceService.save(invoiceRequestDTO);
	}
	
	@GetMapping(path = "/invoices")
	public List<InvoiceResponseDTO> getAllInvoices() {
		return invoiceService.allInvoices();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
