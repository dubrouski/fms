package net.dubrouski.fams.service;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {
	
	public BigDecimal recalculate(String currentBase, String newBase, BigDecimal currentValue);
	
	public BigDecimal getRate(String currentBase, String newBase);
	
	public List<String> getCurrencies();
}
