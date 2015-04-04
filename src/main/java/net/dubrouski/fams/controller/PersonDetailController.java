package net.dubrouski.fams.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
		logger.info("Received person to display: " + p.toString());
		person = personService.getPersonByLegalId(p.getLegalId());
		personsAddresses = personService.getAddressesForPerson(p);

		logger.info("Person was set to :" + person.toString());
		logger.info("Person addresses were set to: "
				+ personsAddresses.toString());
		return "person-detail";
	}

//	@PostConstruct
//	public void init() {
//		person = personService.getPersonByLegalId("LEGAL");
//		personsAddresses = personService.getAddressesForPerson(person);
//	}
}
