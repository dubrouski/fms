package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.to.AccommodationViewModel;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.util.AccommodationConverter;

@Stateful
@Model
public class AccommodationCreationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected AccommodationUnitService accommodationService;

	@Inject
	protected AccommodationConverter converter;

	protected AccommodationViewModel newAccommodation;

	@Produces
	@Named
	public AccommodationViewModel getNewAccommodation() {
		return newAccommodation;
	}

	public String createAccommodation(){
		accommodationService.save(converter.ViewModel2Unit(newAccommodation));
		init();
		return "list?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		newAccommodation = new AccommodationViewModel();
	}
}
