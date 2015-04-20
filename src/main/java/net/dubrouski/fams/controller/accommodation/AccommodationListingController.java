package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean(value = "accommodationListingController")
@SessionScoped
public class AccommodationListingController implements Serializable {
	
//	TODO: class will not load anything - even @PostConstruct does not fire
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccommodationUnitService accommodationService;
	
	@Inject
	private Logger logger;
	
	private List<AccommodationUnit> accommodations;
	
	private int rowCount;
	private int currentPage;
	
	public List<AccommodationUnit> getAccommodations(){
		logger.info("logging");
		return accommodationService.listAccommodations();
	}
	
	public int getRowCount() {
		return rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	@PostConstruct
	public void init() {
		logger.info("message");
	}

}
