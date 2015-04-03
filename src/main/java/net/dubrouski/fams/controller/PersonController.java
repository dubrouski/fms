package net.dubrouski.fams.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class PersonController {

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person newPerson;
	private Person personForDeletion;

	@Produces
	@Named
	public Person getNewPerson() {
		return newPerson;
	}

	@Produces
	@Named
	public Person getPersonForDeletion() {
		return personForDeletion;
	}

	public void createPerson() throws Exception {
		logger.info("Saving new person " + newPerson.toString());
		personService.savePerson(newPerson);
		initNewPerson();
	}

	public String requestPersonDelete(Person person) {
		this.personForDeletion = personService.getPersonByLegalId(person
				.getLegalId());
		if (personForDeletion == null) {
			logger.log(Level.INFO, "requestPersonDelete: personForDeletion is null.");
		}
		return "person-confirm-delete";
	}

	public String confirmPersonDelete(Person person) {

		if (personForDeletion == null) {
			logger.log(Level.INFO, "confirmPersonDelete: personForDeletion is null.");
		}
		
		if (person == null) {
			logger.log(Level.INFO, "confirmPersonDelete: Person is null.");
		}
		if (personService == null) {
			logger.log(Level.INFO, "confirmPersonDelete: PersonService is null.");
		}

		personService.delete(personService.getPersonByLegalId(person
				.getLegalId()));
		return "person-list";
	}

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}
}
