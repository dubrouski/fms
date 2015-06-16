package net.dubrouski.fams.controller.person;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.controller.login.LoginManager;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
//TODO why stateful? 
@Stateful
@Model
public class PersonCreationController {

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person newPerson;

	//TODO remove it
	@ManagedProperty(value = "#{loginManager}")
	private LoginManager loginManager;

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	@Produces
	@Named
	public Person getNewPerson() {
		return newPerson;
	}

	public String createPerson() {
		logger.info("Saving new person " + newPerson.toString());
		personService.savePerson(newPerson);
		
		//TODO add message sending as some helper method?
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
						"Person %s %s created successufully.",
						newPerson.getFirstName(), newPerson.getLastName()),
						null));
		initNewPerson();
		return "list";
	}

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}
}
