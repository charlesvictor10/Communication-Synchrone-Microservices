package dev.charles.dto;

import java.math.BigDecimal;
import java.util.Date;

import dev.charles.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class InvoiceResponseDTO {
	private String id;
	private Date date;
	private BigDecimal amount;
	private Customer customer;
}
