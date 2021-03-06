package net.dubrouski.fams.controller.map;

import net.dubrouski.fams.model.AccommodationUnit;

import org.primefaces.model.diagram.overlay.Overlay;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

public class AccUnitMarker extends Marker implements Overlay {

	private static final long serialVersionUID = 1L;

	private final AccommodationUnit accUnit;

	public AccUnitMarker(LatLng coordinates, String title,
			AccommodationUnit accUnit, String icon) {
		super(coordinates, title, (Object) accUnit, icon);
		this.accUnit = accUnit;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String toJS(StringBuilder sb) {
		return null;
	}

	public AccommodationUnit getAccUnit() {
		return this.accUnit;
	}

}