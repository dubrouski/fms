package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.service.AddressService;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonAddressController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	@Inject
	AddressService addressService;

	@Inject
	PersonDetailController pdc;

	private Person personToCreateAddress;

	private Address newAddress;

	private AddressType addressType = AddressType.Contact;

	public AddressType[] getAddressTypes() {
		return AddressType.values();
	}

	@Produces
	public List<Country> getCountriesList() {
		return addressService.getCountriesList();
	}

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
		personToCreateAddress = person;

		return "address?faces-redirect=true";
	}

	public String addAddressToPerson() {
		addressService.saveAddress(this.newAddress);
		personService.setAddressToPerson(this.personToCreateAddress,
				this.newAddress, this.addressType);

		//TODO replace with helper
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Address successfully added ("
						+ this.newAddress.toShortString() + ")", "Address added"));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);

		return "detail?faces-redirect=true";
	}
}
