package net.dubrouski.fams.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRICE")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "BASE_PRICE")
	private BigDecimal basePrice;
	
	@Column(name = "SERVICES_PRICE")
	private BigDecimal servicesPrice;
	
	@Column(name = "VALID_FROM")
	private Date validFrom;
	
	@Column(name = "VALID_TO")
	private Date validTo;
	
	@Column(name = "CURRENCY")
	@NotNull
	private String currency;

}
