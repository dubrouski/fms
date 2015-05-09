package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;

@ManagedBean
@SessionScoped
public class AccommodationDetailController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AccommodationUnit unit;
	
	private Address address;
	
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
		return "accommodation-detail";
	}
}
