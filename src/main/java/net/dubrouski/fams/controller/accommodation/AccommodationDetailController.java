package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;

import net.dubrouski.fams.model.AccommodationUnit;

@ManagedBean
@SessionScoped
public class AccommodationDetailController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AccommodationUnit unit;
	
	@Inject
	Logger logger;
	
	@Produces
	@Named
	public AccommodationUnit getUnit(){
		logger.info(unit.toString());
		return unit;
	}
	
	public String showDetail(AccommodationUnit u){
		unit = u;
		return "accommodation-detail";
	}
	
	public void handleChildrenToggle(ToggleEvent event){
		
	}
}
