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
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonDetailController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person person;
	private List<PersonAddress> personsAddresses;

	@Produces
	@Named
	public Person getPerson() {
		return person;
	}

	@Produces
	public List<PersonAddress> getPersonsAddresses() {
		return personsAddresses;
	}

	public String showDetail(Person p) {
		this.person = p;
		this.personsAddresses = personService.getAddressesForPerson(p);
		return "person-detail";
	}

	public void handleAddressesToggle(ToggleEvent event) {
		if (event.getVisibility().equals(Visibility.VISIBLE)) {
			loadAddresses();
		}
	}

	public void loadAddresses() {
		this.personsAddresses = personService
				.getAddressesForPerson(this.person);
	}

	public void loadContracts() {
		// add contracts loading here
	}
}
