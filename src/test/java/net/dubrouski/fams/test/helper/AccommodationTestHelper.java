package net.dubrouski.fams.test.helper;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Room;

@Stateless
@Named("accommodationTestHelper")
public class AccommodationTestHelper {
	
	public Place getPlace(boolean isActive, String name, BigDecimal deposit){
		Place place = new Place();
		place.setIsActive(isActive);
		place.setName(name);
		place.setDepositAmount(deposit);
		return place;
	}
	
	public Room getRoom(boolean isActive, String name, BigDecimal deposit){
		Room room = new Room();
		room.setIsActive(isActive);
		room.setName(name);
		room.setDepositAmount(deposit);
		return room;
	}
}
