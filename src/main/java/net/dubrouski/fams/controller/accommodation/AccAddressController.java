package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.service.AddressService;

@ManagedBean
@SessionScoped
public class AccAddressController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccommodationUnitService unitService;
	
	@Inject
	private AccommodationDetailController detailController;
	
	@Inject
	private AddressService addressService;
	
	private AccommodationUnit unitWithAddress;
	
	private Address unitsAddress;
	
	@Inject
	Logger logger;
	
	@Produces
	@Named
	public AccommodationUnit getUnitWithAddress(){
		return unitWithAddress;
	}
	
	@Produces
	public Address getUnitsAddress(){
		return unitsAddress;
	}
	
	@Produces
	public List<Country> getCountriesList() {
		return addressService.getCountriesList();
	}
	
	//TODO: beans not loading, again, even though this time I checked my imports :-/ 
	public String startUpdate(AccommodationUnit unit, Address address){
		unitWithAddress = unit;
		if(address == null){
			logger.info("address initial null");
		}
		if(unit == null){
			logger.info("unit initial null");
		}
		unitsAddress = address;
		return "accommodation-address"; 
	}
	
	public String updateAddress(){
		unitService.updateOrCreateAddress(unitWithAddress, unitsAddress);
		return detailController.showDetail(unitWithAddress);
	}
	

}
