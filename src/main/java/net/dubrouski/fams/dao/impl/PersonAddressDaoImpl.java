package net.dubrouski.fams.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateless
@Named(value = "personAddressDao")
public class PersonAddressDaoImpl extends BaseDaoImpl<PersonAddress, Long>
		implements PersonAddressDao {

	@Inject
	Logger logger;
	
	
	@Override
	public List<PersonAddress> getAddressesForPerson(Person person) {
		if(person == null){
			throw new FmsException("Person cannot be null");
		}
		TypedQuery<PersonAddress> query = this.entityManager
				.createQuery(
						"select adr from Person pers JOIN pers.addresses adr where pers.id = :id order by adr.active desc",
						PersonAddress.class);
		return query.setParameter("id", person.getId()).getResultList();
	}
}
