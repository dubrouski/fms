package net.dubrouski.fams.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.AccommodationUnitService;

@Named(value = "accommodationService")
public class AccommodationUnitServiceImpl implements AccommodationUnitService, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	AccommodationUnitDao unitDao;
	
	@Inject
	PriceDao priceDao;
	
	@Inject
	AddressDao addressDao;
	
	@Inject
	Logger logger;

	@Override
	public void save(AccommodationUnit unit) {
		if(unit.getId() != null){
			throw new FmsException("Cannot save: already existing entity: " + unit);
		}
		logger.info("Saving Accommodation: " + unit);
		unitDao.save(unit);
		logger.info("Accommodation saved: " + unit);
	}

	@Override
	public void delete(AccommodationUnit unit) {		
		logger.info("Deleting Accommodation: " + unit);
		unitDao.delete(unit);
		logger.info("Accommodation was deleted: " + unit);
	}

	@Override
	public void update(AccommodationUnit unit) {
		logger.info("Updating Accommodation: " + unit);
		unitDao.update(unit);
		logger.info("Accommodation was updated: " + unit);
	}

	@Override
	public AccommodationUnit getAccommodationById(Long id) {
		logger.info("Retrieving Accommodation by id: " + id);
		AccommodationUnit found = unitDao.getByID(id);
		logger.info("Accommodation found: " + found);
		return found;
	}

	@Override
	public List<AccommodationUnit> listAccommodations() {
		logger.info("Retrieving all Accommodations...");
		List<AccommodationUnit> found = unitDao.listAll();
		logger.info("Accommodations found: " + found.size());
		return found;
	}

	@Override
	public void setPrice(AccommodationUnit unit, Price price) {
		logger.info("Setting new price for Accommodation: " + unit);
		Price currentPrice = unit.getPrice();
		if(price.getId() == null){
			priceDao.save(price);
		}
		unitDao.setPrice(unit, price);
		if(currentPrice != null){
			priceDao.delete(currentPrice);	
		}
		logger.info("New price was set: " + unit.getPrice());
	}

	@Override
	public void setAddress(AccommodationUnit unit, Address address) {
		logger.info("Setting new address for Accommodation: " + unit);
		Address current = unit.getAddress();
		unitDao.setAddress(unit, address);
		if(current != null && current.getAccommodations().size() == 0){
			addressDao.delete(current);
		}
		logger.info("New address was set: " + unit.getAddress());
	}

	@Override
	public void setAddressWithChildren(AccommodationUnit unit, Address address) {
		logger.info("Setting new address for Accommodation with children: " + unit);
		Address current = unit.getAddress();
		unitDao.setAddressWithChildren(unit, address);
		if(current != null && current.getAccommodations().size() == 0){
			addressDao.delete(current);
		}
		logger.info("New address was set: " + unit.getAddress());
	}

	@Override
	public List<AccommodationUnit> listAccommodationsByType(String type) {
		logger.info("Retrieving Accommodations by type: " + type);
		List<AccommodationUnit> found = unitDao.listAccommodationsByType(type);
		logger.info("Accommodations found: " + found.size());
		return found;
	}
}
