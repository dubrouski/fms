package net.dubrouski.fams.controller;

import java.math.BigDecimal;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Room;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class AccoUnitControllerTEST_old {
	@Inject
	AccommodationUnitDao acuDao;

	@Inject
	AddressDao aDao;

	@Inject
	CountryDao cDao;

	public void createTestData() {

		Place place = new Place();
		place.setName("name");
		place.setIsActive(false);
		place.setDepositAmount(BigDecimal.valueOf(1500));
		acuDao.save(place);
		
		Place place2 = new Place();
		place2.setName("name2");
		place2.setIsActive(false);
		place2.setDepositAmount(BigDecimal.valueOf(1500));
		acuDao.save(place2);
		
		Place place3 = new Place();
		place3.setName("name3");
		place3.setIsActive(false);
		place3.setDepositAmount(BigDecimal.valueOf(1500));
		acuDao.save(place3);

		Room room = new Room();
		room.setName("somename");
		room.setDepositAmount(BigDecimal.valueOf(1500));
		room.setIsActive(false);
//		room.add(place);
//		room.add(place2);
//		room.add(place3);
		acuDao.save(room);

	}
	
	public void setChildren(){
		AccommodationComposite room = (AccommodationComposite)acuDao.getByID(4l);
		AccommodationUnit pl1 = acuDao.getByID(1l);
		AccommodationUnit pl2 = acuDao.getByID(2l);
		AccommodationUnit pl3 = acuDao.getByID(3l);
		
		
		acuDao.addChild(room, pl1);
		acuDao.addChild(room, pl2);
		acuDao.addChild(room, pl3);
	}

	public void setAddressToUnits() {
		Country c = new Country();
		c.setCode("Antigua");
		cDao.save(c);

		Address address = new Address();
		address.setCity("Brno");
		address.setStreetName("Lidicka");
		address.setStreetNumber("15");
		address.setCountry(c);
		address.setFlatNumber("some");
		aDao.save(address);

		AccommodationUnit room = acuDao.getByID(4l);
		
		acuDao.setAddressWithChildren(room, address);
	}
	
	public void setAnotherAddressToUnits() {
		Country c = new Country();
		c.setCode("BSK");
		cDao.save(c);

		Address address = new Address();
		address.setCity("Slonoville");
		address.setStreetName("Kopytova");
		address.setStreetNumber("15");
		address.setCountry(c);
		address.setFlatNumber("some");
		aDao.save(address);

		AccommodationUnit room = acuDao.getByID(4l);
		
		acuDao.setAddressWithChildren(room, address);
	}
}
