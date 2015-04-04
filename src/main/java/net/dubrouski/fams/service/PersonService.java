package net.dubrouski.fams.service;

import java.util.List;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonService {

	public void savePerson(Person person);

	public Person getPersonById(Long id);

	public Person getPersonByLegalId(String id);

	public List<Person> listPersons();

	public void delete(Person person);

	public List<PersonAddress> getAddressesForPerson(Person person);

	public void setAddressToPerson(Person person, Address personAddress,
			AddressType addressType);
	
	public void updatePerson(Person person);
}
