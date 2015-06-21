package net.dubrouski.fams.controller.map;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class AccMapController {

	@Inject
	private Logger logger;

	@Inject
	private AccommodationUnitService accService;

	private MapModel simpleModel;

	private AccUnitMarker marker;

	@PostConstruct
	public void init() {
		// TODO replace with highest level of hierarchy once implemented.
		List<AccommodationUnit> units = accService
				.listAccommodationsByType("room");

		simpleModel = new DefaultMapModel();

		for (AccommodationUnit unit : units) {
			if (unit.getAddress().getLatitude() == null
					|| unit.getAddress().getLongitude() == null) {
				continue;
			}

			LatLng coord = new LatLng(Double.valueOf(unit.getAddress()
					.getLatitude()), Double.valueOf(unit.getAddress()
					.getLongitude()));

			simpleModel.addOverlay(new AccUnitMarker(coord, unit.getAddress()
					.toLongString(), unit, ""));
		}
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (AccUnitMarker) event.getOverlay();
	}

	public AccUnitMarker getMarker() {
		return marker;
	}

}
