package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean
@SessionScoped
public class AccommodationUpdateController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccommodationDetailController detailController;
	
	@Inject
	AccommodationUnitService accommodationService;
	
	private AccommodationUnit unitToUpdate;
	
	@Inject
	Logger logger;
	
	@Produces
	public AccommodationUnit getUnitToUpdate(){
		return unitToUpdate;
	}
	
	public String startUpdate(AccommodationUnit u){
		if (u == null){
			logger.info("unit to update is null");
		}
		unitToUpdate = u;
		return "update?faces-redirect=true";
	}
	
	public String update(AccommodationUnit unit){		
		accommodationService.update(unit);
		return detailController.showDetail(unit);
	}

}
