package net.dubrouski.fams.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class AddressController {

	@Inject
	private Logger logger;

	@Inject
	private PersonDao personDao;

	@Inject
	private PersonAddressDao personAddressDao;

	private Person person;
	private Address newAddress;
	private PersonAddress newPersonAddress;

	public PersonAddress getNewPersonAddress() {
		return this.newPersonAddress;
	}

	public Address getNewAddress() {
		return this.newAddress;
	}

	public Person getPerson() {
		return person;
	}

	@PostConstruct
	public void initNewPersonAddress() {
		newPersonAddress = new PersonAddress();
		newAddress = new Address();
	}

	public String startAddressCreation(Person person) {
		this.person = personDao.getByID(person.getId());
		return "address-create";
	}

	public String saveNewAddress() {
		//addressDao.save(newAddress);
		
		newPersonAddress.setAddress(newAddress);
		
		personAddressDao.save(newPersonAddress);
		return "persons-list";
	}
}
