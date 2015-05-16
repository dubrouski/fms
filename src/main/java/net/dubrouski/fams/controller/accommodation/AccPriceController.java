package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean
@SessionScoped
public class AccPriceController implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Logger logger;
	
	@Inject
	private AccommodationUnitService unitService;
	
	@Inject
	private AccommodationDetailController detailController;
	
	private AccommodationUnit unitWithPrice;
	
	private Price unitsPrice;
	
	@Produces
	public AccommodationUnit getUnitWithPrice(){
		return unitWithPrice;
	}
	
	@Produces 
	public Price getUnitsPrice(){
		return unitsPrice;
	}
	
	public String startUpdate(AccommodationUnit u, Price p){
		if(u == null){
			logger.info("initial unit is null");
		}
		if(p == null){
			logger.info("initial price is null");
		}
		unitWithPrice = u;
		unitsPrice = p;		
		return "price?faces-redirect=true";
	}
	
	public String updatePrice(){
		unitService.setPrice(unitWithPrice, unitsPrice);
		return detailController.showDetail(unitWithPrice);
	}
	
	
	
	
}
