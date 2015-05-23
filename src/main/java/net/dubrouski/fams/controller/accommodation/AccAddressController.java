package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import net.dubrouski.fams.model.AccommodationComposite;
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
	
	private MapModel mapModel;
	
	@Inject
	Logger logger;
	
	@Produces
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
	
	public MapModel getMapModel() {
		return mapModel;
	}
	
	public String startUpdate(AccommodationUnit unit, Address address){
		unitWithAddress = unit;
		if(address == null){
			logger.info("address initial null");
		}
		if(unit == null){
			logger.info("unit initial null");
		}
		unitsAddress = address;
		initializeMapModel();
		return "address?faces-redirect=true"; 
	}
	
	public String updateAddress(){
		AccommodationComposite ac = unitWithAddress.castToComposite();
		if(ac == null || !ac.hasChildren()){
			unitService.updateOrCreateAddress(unitWithAddress, unitsAddress);
		}
		else{
			unitService.updateOrCreateAddressWithChildren(unitWithAddress, unitsAddress);
		}
		return detailController.showDetail(unitWithAddress);
	}
	
	private void initializeMapModel() {
		LatLng coord = new LatLng(Double.valueOf(unitsAddress.getLatitude()),
				Double.valueOf(unitsAddress.getLongitude()));

		// Basic marker
		mapModel = new DefaultMapModel();
		mapModel.addOverlay(new Marker(coord, unitsAddress.toShortString()));
	}
	
	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
	
		this.unitsAddress.setLatitude(latlng.getLat());
		this.unitsAddress.setLongitude(latlng.getLng());
	}
}
