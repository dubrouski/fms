package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
	Logger logger;
	
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
		try{
			return currencyService.getCurrencies();
		}
		catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Could not retrieve currency rates.", "Please try again later."));
			return new ArrayList<String>();
		}
		
	}
	
	public String getNewCurrency(){
		return newCurrency;
	}
	
	public void setNewCurrency(String c){
		newCurrency = c;
	}
	
	public String start(Contract c){
		contract = c;
		price = c.getPrice();
		return "currency";
	}
	
	public String change(){
		try{
			priceService.changeCurrency(getPrice(), newCurrency);
		}
		catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unexpected error occured.", "Could not change currency rates."));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			logger.info("Error when recalculating currency rates");
			return "contract-detail?faces-redirect=true";
		}		
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Currency rate successfully recalculated."));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "contract-detail?faces-redirect=true";
	}
}
