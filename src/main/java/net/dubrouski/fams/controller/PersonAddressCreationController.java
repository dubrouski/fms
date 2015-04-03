package net.dubrouski.fams.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class PersonAddressCreationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person personToCreateAddress;

	private Address newAddress;

	private AddressType addressType;

	public Address getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public Person getPersonToCreateAddress() {
		return personToCreateAddress;
	}

	public void setPersonToCreateAddress(Person personToCreateAddress) {
		this.personToCreateAddress = personToCreateAddress;
	}

	public String startAddressCreation(Person person) {
		newAddress = new Address();
		personToCreateAddress = personService.getPersonByLegalId(person
				.getLegalId());

		return "person-create-address";
	}

	public String addAddressToPerson() {
		personService.setAddressToPerson(this.personToCreateAddress,
				this.newAddress, this.addressType);

		return "person-list";
	}

}
