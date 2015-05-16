package net.dubrouski.fams.dao;

import java.util.List;

import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Price;
/**
 * 
 * @author ondrej.prazak
 *
 */
public interface AccommodationUnitDao extends BaseDao<AccommodationUnit, Long>{
	
	public AccommodationUnit getParent(AccommodationUnit unit);
	
	public boolean hasParent(AccommodationUnit unit);
	
	public void addChild(AccommodationComposite parent, AccommodationUnit child);
	
	public void removeChild(AccommodationComposite parent, AccommodationUnit child);
	
	public void setAddress(AccommodationUnit unit, Address address);
	
	public void setAddressWithChildren(AccommodationUnit unit, Address address);
	
	public void setPrice(AccommodationUnit unit, Price price);
	
	public List<AccommodationUnit> listAccommodationsByType(String type);
	
	List<AccommodationUnit> getAccommodationsByPage(int pageSize, int first);

}
