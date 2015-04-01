package net.dubrouski.fams.controller;

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

	private Person personDetail;

	@Produces
	@Named
	public Person getNewPerson() {
		return newPerson;
	}
	
	@Produces
	@Named
	public Person getPersonDetail() {
		return personDetail;
	}

	public void createPerson() throws Exception {
		logger.info("Saving new person " + newPerson.toString());
		personService.savePerson(newPerson);
		initNewPerson();
	}

	public String showDetail(Person person) {
		personDetail = personService.getPersonById(person.getId());
		return "person-detail";
	}

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}
}
