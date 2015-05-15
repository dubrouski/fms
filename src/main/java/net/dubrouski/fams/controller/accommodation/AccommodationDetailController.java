package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.AccommodationUnitService;


@ManagedBean
@SessionScoped
public class AccommodationDetailController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AccommodationUnit unit;
	
	private Address address;
	
	private Price price;
	
	@Inject
	AccommodationUnitService unitService;
	
	@Inject
	Logger logger;
	
	@Produces
	@Named
	public AccommodationUnit getUnit(){
		return unit;
	}
	
	@Produces
	public Address getAddress(){
		return address;
	}
	
	@Produces
	public Price getPrice(){
		return price;
	}
	
	public boolean hasUnitParent(){
		
		return unitService.hasParent(unit);
	}
	
	public AccommodationComposite compositeUnit(){
		if(unit instanceof AccommodationComposite){
			return (AccommodationComposite) unit;
		}
		return null;		
	}	
	
	public String showDetail(AccommodationUnit u){
		unit = u;
		if(unit.getAddress() == null){
			address = new Address();
			logger.info("new address");
		}
		else{
			address = unit.getAddress();
			logger.info(address.toString());
		}
		if(unit.getPrice() == null){
			price = new Price();
			logger.info("new price");
		}
		else{
			price = unit.getPrice();
			logger.info(price.toString());
		}
		return "accommodation-detail";
	}
}
