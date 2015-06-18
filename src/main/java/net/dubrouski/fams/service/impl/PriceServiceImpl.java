package net.dubrouski.fams.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.CurrencyService;
import net.dubrouski.fams.service.PriceService;
import net.dubrouski.fams.validator.EntityValidator;

@Named
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PriceServiceImpl implements Serializable, PriceService{

	private static final long serialVersionUID = 1L;
	
	@Inject
	PriceDao priceDao;
	
	@Inject
	CurrencyService currencyService;

	@Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Price price) {
		priceDao.update(price);
	}
	
	@Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
