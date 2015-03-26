package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.validator.EntityValidator;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Stateless
@Named(value = "accommodationUnitDao")
public class AccommodationUnitDaoImpl extends BaseDaoImpl<AccommodationUnit, Long> 
									  implements AccommodationUnitDao{

	public AccommodationUnit getParent(AccommodationUnit unit){
		EntityValidator.validate(unit);
		Query q = entityManager.createQuery(
			"SELECT u FROM AccommodationUnit u JOIN u.children p WHERE p.child_id = :id");
		q.setParameter("id", unit.getId());
		return (AccommodationUnit) q.getSingleResult();
	}
	
	public void addChild(AccommodationComposite parent, AccommodationUnit child) {
		EntityValidator.validate(parent);
		EntityValidator.validate(child);
		parent.add(child);
		update(parent);
	}
	
	public void removeChild(AccommodationComposite parent, AccommodationUnit child) {
		EntityValidator.validate(parent);
		EntityValidator.validate(child);
		parent.remove(child);
		update(parent);
	}
	
//	When more composite elements are added in the future, Address will need refactoring.
//	There probably will be table inheritance hierarchy with @MappedSuperclass  
//	and this method will be rewritten completely.
	public void setAddress(AccommodationUnit unit, Address address){
		EntityValidator.validate(unit);
		EntityValidator.validate(address);
		if(!(unit instanceof Place)){
			if(((AccommodationComposite)unit).childrenCount() > 0) {
				throw new FmsException("Cannot change address if unit has children.");
			}
		}
		if(getParent(unit) != null) {
			throw new FmsException("Cannot change address if unit has parent.");
		}
		unit.setAddress(address);
		update(unit);
	}
	
	public void setAddressWithChildren(AccommodationUnit unit, Address address){
		EntityValidator.validate(unit);
		EntityValidator.validate(address);
		if(getParent(unit) != null) {
			throw new FmsException("Cannot change address if unit has parent.");
		}
		setAddressForChildren(unit, address);
	}
	
	private void setAddressForChildren(AccommodationUnit unit, Address address){
		if((unit instanceof Place) || (((AccommodationComposite)unit).childrenCount() == 0)){
			unit.setAddress(address);
			update(unit);
		}
		else{
			AccommodationComposite composite = (AccommodationComposite) unit;
			composite.setAddress(address);
			update(composite);
			for(AccommodationUnit u : composite.getChildren()){				
				setAddressForChildren(u, address);				
			}
		}
	}

}
