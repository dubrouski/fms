package net.dubrouski.fams.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.model.enums.SortingOrder;
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
	
	@Resource(mappedName = "java:/jms/queue/personQueue")
	private Queue personQueue;

	@Inject
	JMSContext context;

	@Override
	public void savePerson(Person person) {
		personDao.save(person);
		logger.log(Level.INFO, "Created new person: " + person.toString());
		context.createProducer().send(personQueue, person);
		logger.info("Sending message to queue PersonQueue" + person.toString());
	}

	@Override
	public void updatePerson(Person person) {
		personDao.update(person);
	}

	@Override
	public void delete(Person person) {
		logger.log(Level.INFO, "Requested deletion of person: " + person);
		personDao.delete(person);
		logger.log(Level.INFO, "Person successfully deleted: " + person);
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
		return result;
	}

	@Override
	public void setAddressToPerson(Person person, Address address,
			AddressType addressType) {
		Person personWithAddresses = this
				.getPersonWithAddresses(person.getId());
		PersonAddress personAddress = new PersonAddress();
		personAddress.setAddress(address);
		personAddress.setAddressType(addressType);

		personAddressDao.save(personAddress);

		// deactivate previous address of the same type
		for (PersonAddress pa : personWithAddresses.getAddresses()) {
			if (pa.getAddressType().equals(personAddress.getAddressType())) {
				pa.setActive(false);
				personAddressDao.update(pa);
			}
		}

		personWithAddresses.addAddress(personAddress);
		personDao.update(personWithAddresses);
	}

	@Override
	public List<PersonAddress> getAddressesForPerson(Person person) {
		List<PersonAddress> result = personAddressDao
				.getAddressesForPerson(person);

		return result;
	}

	private Person getPersonWithAddresses(Long id) {
		return personDao.getPersonWithAddresses(id);
	}

	@Override
	public List<Person> listPersons(int pageSize, int first,
			String sortField, SortingOrder sortingOrder,
			String searchTerm) {
		logger.log(Level.INFO, String.format("Listing for %d, %d, %s %s",
				pageSize, first, sortField, sortingOrder.name()));

		return personDao.listPersons(pageSize, first, sortField,
				sortingOrder, searchTerm);
	}

	@Override
	public long getPersonsCount() {
		return personDao.getPersonsCount();
	}
}
