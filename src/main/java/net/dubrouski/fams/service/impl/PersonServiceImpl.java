package net.dubrouski.fams.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@Named(value = "personService")
public class PersonServiceImpl implements PersonService {

	@Inject
	PersonDao personDao;

	@Inject
	PersonAddressDao personAddressDao;

	@Inject
	AddressDao addressDao;

	@Inject
	Logger logger;

	@Override
	public void savePerson(Person person) {
		personDao.save(person);
		logger.log(Level.INFO, "Created new person: " + person.toString());
	}

	@Override
	public Person getPersonById(Long id) {
		Person result = personDao.getByID(id);
		logger.log(Level.INFO, "Method getPersonById, id: " + id
				+ ", found person: " + result.toString());
		return result;
	}

	@Override
	public List<Person> listPersons() {
		return personDao.listAll();
	}

	public Person getPersonByLegalId(String id) {
		Person result = personDao.getByLegalId(id);
		logger.log(Level.INFO, "Method getPersonByLegalId, id: " + id
				+ ", found person: " + result.toString());
		return result;
	}

	@Override
	public void delete(Person person) {
		logger.log(Level.INFO, "Method delete (person), person to delete: "
				+ person);
		personDao.delete(person);
		logger.log(Level.INFO, "Method delete (person), person was deleted: "
				+ person);
	}

	@Override
	public void setAddressToPerson(Person person, Address address,
			AddressType addressType) {
		Person localPerson = personDao.getByID(person.getId());
		
		PersonAddress personAddress = new PersonAddress();
		personAddress.setAddress(address);
		personAddress.setAddressType(addressType);

		personAddressDao.save(personAddress);

		localPerson.addAddress(personAddress);
		personDao.update(localPerson);

	}

	@Override
	public List<PersonAddress> getAddressesForPerson(Person person) {
		logger.info("Received person to return addresses for: " + person);
		List<PersonAddress> result = personAddressDao
				.getAddressesForPerson(person);
		logger.info("Retrieved addresses: " + result.toString());
		return result;
	}

	@Override
	public void updatePerson(Person person) {
		personDao.update(person);
	}
}
