package dev.charles.service;

import java.util.List;

import dev.charles.dto.InvoiceRequestDTO;
import dev.charles.dto.InvoiceResponseDTO;

public interface InvoiceService {
	InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
	InvoiceResponseDTO getOneInvoice(String id);
	List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
	List<InvoiceResponseDTO> allInvoices();
}
