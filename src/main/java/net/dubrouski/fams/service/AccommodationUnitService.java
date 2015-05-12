package net.dubrouski.fams.service;

import java.util.List;

import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;

/**
 * 
 * @author ondrej.prazak
 *
 */
public interface AccommodationUnitService {

	public void save(AccommodationUnit unit);

	public void delete(AccommodationUnit unit);

	public void update(AccommodationUnit unit);

	public AccommodationUnit getAccommodationById(Long id);

	public List<AccommodationUnit> listAccommodations();

	public void setPrice(AccommodationUnit unit, Price price);

	public void setAddress(AccommodationUnit unit, Address address);

	public void setAddressWithChildren(AccommodationUnit unit, Address address);

	public List<AccommodationUnit> listAccommodationsByType(String type);

	public List<AccommodationUnit> getAccommodationsByPage(int pageSize,
			int first);
	
	public void updateOrCreateAddress(AccommodationUnit unit, Address address);
	
	public void createNewChild(AccommodationComposite parent, AccommodationUnit child);
	
	public AccommodationUnit getParent(AccommodationUnit unit);
	
	public boolean hasParent(AccommodationUnit unit);
	
}
