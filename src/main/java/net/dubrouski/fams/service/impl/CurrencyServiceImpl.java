package net.dubrouski.fams.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Rates;
import net.dubrouski.fams.rest.CurrencyConversionClient;
import net.dubrouski.fams.service.CurrencyService;

@Named
public class CurrencyServiceImpl implements Serializable, CurrencyService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	CurrencyConversionClient client;

	@Override
	public BigDecimal recalculate(String currentBase, String newBase, BigDecimal currentValue) {
		Rates r = client.createClient(currentBase);
		BigDecimal rate = BigDecimal.valueOf(r.getRate(newBase));
		if(rate == null){
			throw new FmsException("Desired currency not found");
		}
		return currentValue.multiply(rate);
	}

}
