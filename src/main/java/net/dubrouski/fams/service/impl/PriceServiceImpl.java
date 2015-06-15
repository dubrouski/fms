package net.dubrouski.fams.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.inject.Inject;

import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.CurrencyService;
import net.dubrouski.fams.service.PriceService;
import net.dubrouski.fams.validator.EntityValidator;

public class PriceServiceImpl implements Serializable, PriceService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	PriceDao priceDao;
	
	@Inject
	CurrencyService currencyService;

	@Override
	public void update(Price price) {
		priceDao.update(price);
	}
	
	@Override
	public void changeCurrency(Price p, String newCurrency){
		EntityValidator.validate(p);
		EntityValidator.validateId(p);
		if(newCurrency == null){
			throw new FmsException("New currency is null");
		}
		BigDecimal rate = currencyService.getRate(p.getCurrency(), newCurrency);
		p.setBasePrice(p.getBasePrice().multiply(rate).setScale(2, RoundingMode.CEILING).stripTrailingZeros());
		p.setServicesPrice(p.getServicesPrice().multiply(rate));
		p.setCurrency(newCurrency);
		priceDao.update(p);
	}
	
}
