package dev.charles.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.charles.dto.InvoiceRequestDTO;
import dev.charles.dto.InvoiceResponseDTO;
import dev.charles.entities.Customer;
import dev.charles.entities.Invoice;
import dev.charles.exceptions.CustomerNotFoundException;
import dev.charles.mappeurs.InvoiceMapper;
import dev.charles.openfeign.CustomerRestClient;
import dev.charles.repositories.InvoiceRepository;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	private InvoiceRepository invoiceRepository;
	private InvoiceMapper invoiceMapper;
	private CustomerRestClient customerRestClient;
		
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper,
			CustomerRestClient customerRestClient) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.invoiceMapper = invoiceMapper;
		this.customerRestClient = customerRestClient;
	}

	@Override
	public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
		/**
		 * Vérification de l'intégrité référentielle Invoice / Customer 
		 */
		Customer customer = null;
		try {
			customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
		} catch(Exception e) {
			throw new CustomerNotFoundException("Customer not found");
		}		
		Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
		invoice.setId(UUID.randomUUID().toString());
		invoice.setDate(new Date());		
		Invoice saveInvoice = invoiceRepository.save(invoice);
		saveInvoice.setCustomer(customer);
		InvoiceResponseDTO invoiceResponseDTO = invoiceMapper.fromInvoice(saveInvoice);
		return invoiceResponseDTO;
	}

	@Override
	public InvoiceResponseDTO getOneInvoice(String id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
		invoice.setCustomer(customer);
		InvoiceResponseDTO invoiceResponseDTO = invoiceMapper.fromInvoice(invoice);
		return invoiceResponseDTO;
	}

	@Override
	public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
		List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
		invoices.forEach(invoice -> {
			Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
			invoice.setCustomer(customer);
		});
		List<InvoiceResponseDTO> invoicesResponseDTOs = invoices.stream()
				.map(invoid -> invoiceMapper.fromInvoice(invoid))
				.collect(Collectors.toList());
		return invoicesResponseDTOs;
	}

	@Override
	public List<InvoiceResponseDTO> allInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
		invoices.forEach(invoice -> {
			Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
			invoice.setCustomer(customer);
		});
		List<InvoiceResponseDTO> invoiceResponseDTOs = invoices.stream()
				.map(invoid -> invoiceMapper.fromInvoice(invoid))
				.collect(Collectors.toList());
		return invoiceResponseDTOs;
	}

}
