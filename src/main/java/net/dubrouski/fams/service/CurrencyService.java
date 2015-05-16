package net.dubrouski.fams.service;

import java.math.BigDecimal;

public interface CurrencyService {
	
	public BigDecimal recalculate(String currentBase, String newBase, BigDecimal currentValue);
}
