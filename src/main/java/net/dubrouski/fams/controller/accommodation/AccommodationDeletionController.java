package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean
@SessionScoped
public class AccommodationDeletionController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	AccommodationUnitService accommodationService;
	
	@Inject
	Logger logger;
	
	private AccommodationUnit unitToDelete;
	
	@Produces
	@Named
	public AccommodationUnit getUnitToDelete(){
		return unitToDelete;
	}
	
	
	public String delete(AccommodationUnit unit){
		if (unit == null) {
			logger.info("unit to delete is null");	
		}
		
		unitToDelete = unit;
		return "delete?faces-redirect=true";
	}
	
	public String confirmDelete(){
		accommodationService.delete(unitToDelete);
		return "list";
	}

}
