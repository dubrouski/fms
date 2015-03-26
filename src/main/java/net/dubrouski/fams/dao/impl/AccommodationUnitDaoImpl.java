package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
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
		Query q = getEntityManager().createQuery(
			"SELECT u FROM AccommodationUnit u JOIN u.children p WHERE p.child_id = :id");
		q.setParameter("id", unit.getId());
		return (AccommodationUnit) q.getSingleResult();
	}
	
	public void addChild(AccommodationComposite parent, AccommodationUnit child) {
		parent.add(child);
		update(parent);
	}
	
	public void removeChild(AccommodationComposite parent, AccommodationUnit child) {
		parent.remove(child);
		update(parent);
	}
	
//	public void setAddress(AccommodationUnit unit, Address address){
//		
//	}

}
