package dev.charles.mappeurs;

import org.mapstruct.Mapper;

import dev.charles.dto.InvoiceRequestDTO;
import dev.charles.dto.InvoiceResponseDTO;
import dev.charles.entities.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
	Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
	InvoiceResponseDTO fromInvoice(Invoice invoice);
}
