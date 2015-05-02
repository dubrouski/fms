package net.dubrouski.fams.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.validator.EntityValidator;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Stateless
@Named(value = "unitDao")
public class AccommodationUnitDaoImpl extends BaseDaoImpl<AccommodationUnit, Long> 
									  implements AccommodationUnitDao{

	@Inject
	Logger logger;
	
	@Override
	public AccommodationUnit getParent(AccommodationUnit unit){
		EntityValidator.validate(unit);
		Query q = entityManager.createQuery(
			"SELECT u FROM AccommodationUnit u JOIN u.children p WHERE p.id = :id");
		q.setParameter("id", unit.getId());
		List<?> results = q.getResultList();
		return (AccommodationUnit) (results.size() > 0 ? results.get(0) : null);
	}
	
	@Override
	public void addChild(AccommodationComposite parent, AccommodationUnit child) {
		EntityValidator.validate(parent);
		EntityValidator.validate(child);
		parent.add(child);
		update(parent);
	}
	
	@Override
	public void removeChild(AccommodationComposite parent, AccommodationUnit child) {
		EntityValidator.validate(parent);
		EntityValidator.validate(child);
		parent.remove(child);
		update(parent);
	}
	
//	When more composite elements are added in the future, Address will need refactoring.
//	There probably will be table inheritance hierarchy  
//	and this method will be rewritten completely.
	@Override
	public void setAddress(AccommodationUnit unit, Address address){
		EntityValidator.validate(unit);
		EntityValidator.validate(address);
		if(!(unit instanceof Place)){
			if(((AccommodationComposite)unit).childrenCount() > 0) {
				throw new FmsException("Cannot change address if unit has children.");
			}
		}
		if(getParent(unit) != null) {
			logger.info("AccommodationUnit has parent, setting address to be same as parent's.");
			unit.setAddress(getParent(unit).getAddress());
			update(unit);
		}
		else{
			unit.setAddress(address);
			update(unit);	
		}
	}
	
	@Override
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

	@Override
	public void setPrice(AccommodationUnit unit, Price price) {
		EntityValidator.validate(unit);
		if(price != null){
			EntityValidator.validateId(price);
		}
		unit.setPrice(price);
		update(unit);
	}

	@Override
	public List<AccommodationUnit> listAccommodationsByType(String type) {
		if(type.equals("room") || type.equals("place")){
			Query q = entityManager.createQuery("SELECT u FROM AccommodationUnit u WHERE u.type = :type");
			q.setParameter("type", type);
			return q.getResultList();
		}
		else{
			throw new FmsException("Unknown AccommodationUnit type");
		}		
	}

	@Override
	public List<AccommodationUnit> getAccommodationsByPage(int pageSize,
			int first) {
		TypedQuery<AccommodationUnit> query = this.entityManager
				.createQuery("select p from AccommodationUnit p", AccommodationUnit.class)
				.setFirstResult(first).setMaxResults(pageSize);

		return query.getResultList();
	}

}
