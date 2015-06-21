package net.dubrouski.fams.controller.person;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.controller.ControllerHelper;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class PersonCreationController {

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person newPerson;

	@Produces
	@Named
	public Person getNewPerson() {
		return newPerson;
	}

	public String createPerson() {
		logger.info("Saving new person " + newPerson.toString());
		personService.savePerson(newPerson);

		ControllerHelper.addInfoMessage(
				String.format("Person %s %s created successufully.",
						newPerson.getFirstName(), newPerson.getLastName()),
				null, false);
		initNewPerson();
		return "list";
	}

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}
}
