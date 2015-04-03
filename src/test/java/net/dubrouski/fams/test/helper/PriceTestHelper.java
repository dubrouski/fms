package net.dubrouski.fams.test.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.model.Price;

@Stateless
@Named(value = "priceTestHepler")
public class PriceTestHelper {
	
	public Price getTestPrice(BigDecimal basePrice,
							  BigDecimal servicesPrice,
							  LocalDate validFrom,
							  LocalDate validTo,
							  String currency){
		Price p = new Price();
		p.setBasePrice(basePrice);
		p.setServicesPrice(servicesPrice);
		p.setValidFrom(validFrom);
		p.setValidTo(validTo);
		p.setCurrency(currency);
		return p;
	}
}
