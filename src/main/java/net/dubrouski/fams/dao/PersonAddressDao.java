package net.dubrouski.fams.dao;

import java.util.List;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonAddressDao extends BaseDao<PersonAddress, Long> {
	public List<PersonAddress> getAddressesForPerson(Person person);
}
