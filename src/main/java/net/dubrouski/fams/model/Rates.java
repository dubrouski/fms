package net.dubrouski.fams.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Rates {
	
	private String base;
	
	private String date;
	
	private Map<String, Double> rates;
	
	public String getBase(){
		return base;
	}

	public String getDate() {
		return date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setRates(Map<String, Double> ratesList) {
		this.rates = ratesList;
	}
	
	@Override
	public String toString(){
		String m = "";
		for(String s : rates.keySet()){
			m += s + ", ";
		}
		return base + " " + date + " " + m;
	}
	
	public List<String> currencies(){
		return  new ArrayList<String>(rates.keySet());
	}
	
	public Double getRate(String currencyCode){
		return rates.get(currencyCode);
	}
	
	
}
