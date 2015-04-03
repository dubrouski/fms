package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;
/**
 * 
 * @author ondrej.prazak
 *
 */
public interface AccommodationUnitDao extends BaseDao<AccommodationUnit, Long>{
	
	public AccommodationUnit getParent(AccommodationUnit unit);
	
	public void addChild(AccommodationComposite parent, AccommodationUnit child);
	
	public void removeChild(AccommodationComposite parent, AccommodationUnit child);
	
	public void setAddress(AccommodationUnit unit, Address address);
	
	public void setAddressWithChildren(AccommodationUnit unit, Address address);
	
	public void setPrice(AccommodationUnit unit, Price price);

}
