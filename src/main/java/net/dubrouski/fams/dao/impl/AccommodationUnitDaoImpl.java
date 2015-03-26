package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.model.AccommodationUnit;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Stateless
@Named(value = "accommodationUnitDao")
public class AccommodationUnitDaoImpl extends BaseDaoImpl<AccommodationUnit, Long> 
									  implements AccommodationUnitDao{

	

}
