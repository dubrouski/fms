package net.dubrouski.fams.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.PersonAddressDao;
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

	//TODO redo select query 
	@Override
	public List<PersonAddress> getAddressesForPerson(Person person) {
		TypedQuery<PersonAddress> query = this.entityManager.createQuery(
				"select pa from PersonAddress pa", PersonAddress.class);
		return query.getResultList();

	}

}
