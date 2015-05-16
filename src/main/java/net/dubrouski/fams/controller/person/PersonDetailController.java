package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.service.ContractService;
import net.dubrouski.fams.service.PersonService;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonDetailController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Logger logger;

	@Inject
	PersonService personService;

	@Inject
	ContractService contractService;

	private Person person;
	private List<PersonAddress> personsAddresses;
	private List<Contract> personsContracts;

	@Produces
	@Named
	public Person getPerson() {
		return person;
	}

	@Produces
	public List<PersonAddress> getPersonsAddresses() {
		return personsAddresses;
	}

	@Produces
	public List<Contract> getPersonsContracts() {
		return personsContracts;
	}

	public String showDetail(Person p) {
		this.person = p;

		logger.log(Level.INFO, "show detail for person " + p);

		this.personsAddresses = personService.getAddressesForPerson(p);
		this.personsContracts = contractService.getContractsByPerson(p);

		logger.log(Level.INFO, "lists loaded for person " + p);

		return "detail?faces-redirect=true";
	}

	public void handleAddressesToggle(ToggleEvent event) {
		if (event.getVisibility().equals(Visibility.VISIBLE)) {
			loadAddresses();
		}
	}

	public void handleContractsToggle(ToggleEvent event) {
		if (event.getVisibility().equals(Visibility.VISIBLE)) {
			loadContracts();
		}
	}

	public void loadAddresses() {
		this.personsAddresses = personService
				.getAddressesForPerson(this.person);
	}

	public void loadContracts() {
		this.personsContracts = contractService
				.getContractsByPerson(this.person);
	}
}
