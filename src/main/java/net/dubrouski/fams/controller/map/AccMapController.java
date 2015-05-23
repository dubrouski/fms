package net.dubrouski.fams.controller.map;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.service.AddressService;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

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
	private AddressService addrService;

	private MapModel simpleModel;

	@PostConstruct
	public void init() {

		List<Address> addresses = addrService.listAddresses();

		simpleModel = new DefaultMapModel();

		for (Address addr : addresses) {
			if (addr.getLatitude() == 0 || addr.getLongitude() == 0) {
				continue;
			}

			LatLng coord = new LatLng(Double.valueOf(addr.getLatitude()),
					Double.valueOf(addr.getLongitude()));

			// Basic marker
			simpleModel.addOverlay(new Marker(coord, addr.toShortString()));
		}
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}
}
