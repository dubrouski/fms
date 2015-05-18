package net.dubrouski.fams.service;

import net.dubrouski.fams.model.Price;

public interface PriceService {
	
	public void update(Price price);
	
	public void changeCurrency(Price price, String newCurrency);
}
