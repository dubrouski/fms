package net.dubrouski.fams.util;

import javax.inject.Named;

import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.AccommodationViewModel;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Room;

@Named("accommodationConverter")
public class AccommodationConverter {

	public AccommodationUnit ViewModel2Unit(AccommodationViewModel model) {
		if(model.getType().equals("place")){
			Place p = new Place();
			p.setDepositAmount(model.getDepositAmount());
			p.setIsActive(model.getIsActive());
			p.setName(model.getName());
			return p;
		} else if (model.getType().equals("room")){
			Room r = new Room();
			r.setDepositAmount(model.getDepositAmount());
			r.setIsActive(model.getIsActive());
			r.setName(model.getName());
			return r;
		}
		else{
			throw new FmsException("Unknown accommodation model type");
		}
	}
}
