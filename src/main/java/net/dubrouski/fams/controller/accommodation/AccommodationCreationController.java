package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.AccommodationViewModel;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.util.AccommodationConverter;

@Stateful
@Model
public class AccommodationCreationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AccommodationUnitService accommodationService;

	@Inject
	private AccommodationConverter converter;

	private AccommodationViewModel newAccommodation;

	@Produces
	@Named
	public AccommodationViewModel getNewAccommodation() {
		return newAccommodation;
	}

	public String createAccommodation() throws Exception {
		accommodationService.save(converter.ViewModel2Unit(newAccommodation));
		;
		init();
		return "accommodation-list";
	}

	@PostConstruct
	public void init() {
		newAccommodation = new AccommodationViewModel();
	}
}
