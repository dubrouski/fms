package net.dubrouski.fams.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.AccommodationUnitService;

@Named(value = "accommodationService")
@Stateful
@RolesAllowed("accommAdmin")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccommodationUnitServiceImpl implements AccommodationUnitService,
		Serializable {

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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(AccommodationUnit unit) {
		if (unit.getId() != null) {
			throw new FmsException("Cannot save: already existing entity: "
					+ unit);
		}
		logger.info("Saving Accommodation: " + unit);
		unitDao.save(unit);
		logger.info("Accommodation saved: " + unit);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(AccommodationUnit unit) {
		logger.info("Deleting Accommodation: " + unit);
		unitDao.delete(unit);
		logger.info("Accommodation was deleted: " + unit);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(AccommodationUnit unit) {
		logger.info("Updating Accommodation: " + unit);
		unitDao.update(unit);
		logger.info("Accommodation was updated: " + unit);
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public AccommodationUnit getAccommodationById(Long id) {
		logger.info("Retrieving Accommodation by id: " + id);
		AccommodationUnit found = unitDao.getByID(id);
		logger.info("Accommodation found: " + found);
		return found;
	}

	@Override
	@PermitAll
	public List<AccommodationUnit> listAccommodations() {
		logger.info("Retrieving all Accommodations...");
		List<AccommodationUnit> found = unitDao.listAll();
		logger.info("Accommodations found: " + found.size());
		return found;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setPrice(AccommodationUnit unit, Price price) {
		logger.info("Setting new price for Accommodation: " + unit);
		Price currentPrice = unit.getPrice();
		if (price.getId() == null) {
			priceDao.save(price);
		}
		unitDao.setPrice(unit, price);
		if (currentPrice != null) {
			priceDao.delete(currentPrice);
		}
		logger.info("New price was set: " + unit.getPrice());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setAddress(AccommodationUnit unit, Address address) {
		logger.info("Setting new address for Accommodation: " + unit);
		Address current = unit.getAddress();
		unitDao.setAddress(unit, address);
		if (current != null && current.getAccommodations().size() == 0) {
			addressDao.delete(current);
		}
		logger.info("New address was set: " + unit.getAddress());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setAddressWithChildren(AccommodationUnit unit, Address address) {
		logger.info("Setting new address for Accommodation with children: "
				+ unit);
		Address current = unit.getAddress();
		unitDao.setAddressWithChildren(unit, address);
		if (current != null && current.getAccommodations().size() == 0) {
			addressDao.delete(current);
		}
		logger.info("New address was set: " + unit.getAddress());
	}

	@Override
	@PermitAll
	public List<AccommodationUnit> listAccommodationsByType(String type) {
		logger.info("Retrieving Accommodations by type: " + type);
		List<AccommodationUnit> found = unitDao.listAccommodationsByType(type);
		logger.info("Accommodations found: " + found.size());
		return found;
	}

	@Override
	@PermitAll
	public List<AccommodationUnit> getAccommodationsByPage(int pageSize,
			int first) {
		logger.info("Retrieving Accommodations by page " + pageSize + ", "
				+ first);
		List<AccommodationUnit> found = unitDao.getAccommodationsByPage(
				pageSize, first);
		logger.info("Accommodations found: " + found.size());
		return found;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateOrCreateAddress(AccommodationUnit unit, Address address) {
		if (address.getId() == null) {
			addressDao.save(address);
			setAddress(unit, address);
		} else {
			addressDao.update(address);
		}
	}

	@Override
	public void updateOrCreateAddressWithChildren(AccommodationUnit unit,
			Address address) {
		if (address.getId() == null) {
			addressDao.save(address);
			setAddressWithChildren(unit, address);
		} else {
			addressDao.update(address);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createNewChild(AccommodationComposite parent,
			AccommodationUnit child) {
		unitDao.save(child);
		unitDao.addChild(parent, child);
	}

	@Override
	@PermitAll
	public AccommodationUnit getParent(AccommodationUnit unit) {
		return unitDao.getParent(unit);
	}

	@Override
	@PermitAll
	public boolean hasParent(AccommodationUnit unit) {
		return unitDao.hasParent(unit);
	}
}
