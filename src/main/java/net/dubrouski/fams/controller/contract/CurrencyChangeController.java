package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.CurrencyService;
import net.dubrouski.fams.service.PriceService;

@ManagedBean
@SessionScoped
public class CurrencyChangeController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Contract contract;
	
	private Price price;
	
	@Inject
	CurrencyService currencyService; 
	
	@Inject
	private PriceService priceService;
	
	public Contract getContract(){
		return contract;
	}
	
	public Price getPrice(){
		return price;
	}
	
	private String newCurrency;
	
	public List<String> currencies(){
		return currencyService.getCurrencies();
	}
	
	public String getNewCurrency(){
		return newCurrency;
	}
	
	public String start(Contract c){
		contract = c;
		price = c.getPrice();
		return "currency";
	}
	
	public String change(){
		//TODO: finish this
		try{
			priceService.changeCurrency(getPrice(), newCurrency);
		}
		catch(Exception ex){
			//TODO
		}
		return "contract-detail";
	}
}
