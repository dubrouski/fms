package net.dubrouski.fams.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import net.dubrouski.fams.annotations.DateSuccession;
import net.dubrouski.fams.converter.LocalDatePersistenceConverter;

@Entity
@Table(name = "PRICE")
@DateSuccession
public class Price implements Serializable, BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "BASE_PRICE")
	@Min(value = 0)
	@NotNull
	private BigDecimal basePrice;
	
	@Column(name = "SERVICES_PRICE")
	@Min(value = 0)
	@NotNull
	private BigDecimal servicesPrice;
	
	@Column(name = "VALID_FROM")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate validFrom;
	
	@Column(name = "VALID_TO")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate validTo;
	
	@Column(name = "CURRENCY")
	@NotNull
	@Pattern(regexp="[a-zA-Z]+")
	private String currency;
	
	public Price(){}
	
	public Price(Price clone) {
		this.basePrice = clone.getBasePrice();
		this.servicesPrice = clone.getServicesPrice();
		this.currency = clone.getCurrency();
	}
	
	public boolean validateDates(){
		if(validFrom != null && validTo != null){
			return validFrom.isBefore(validTo);	
		}
		else {
			return true;
		}		
	}
	
	public BigDecimal getTotalPrice(){
		try{
			return basePrice.add(servicesPrice);
		}
		catch(NullPointerException ex){
			return null;
		}
	} 
	
	@Override
	public String toString(){
		return getClass().toString() + ": id: " + id + ": currency: " + currency; 
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Price p = (Price) obj;
        if (this.getId() != p.getId() && (this.getId() == null || !this.getId().equals(p.getId()))) {
            return false;
        }
        return true;
    }
	
	@Override
    public int hashCode() {
        return 7 * currency.hashCode()
        		+ 13 * basePrice.hashCode() + 17 * servicesPrice.hashCode();
    }

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getServicesPrice() {
		return servicesPrice;
	}

	public void setServicesPrice(BigDecimal servicesPrice) {
		this.servicesPrice = servicesPrice;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
