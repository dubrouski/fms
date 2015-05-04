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
import net.dubrouski.fams.annotations.LoggedIn;
import net.dubrouski.fams.controller.LoginManager;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.enums.UserRightIds;
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

	@Inject
	@LoggedIn
	private User loggedUser;

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
		if (loggedUser != null
				&& loggedUser.hasRight(UserRightIds.PERSON_WRITE)) {
			logger.info("Saving new person " + newPerson.toString());
			personService.savePerson(newPerson);
			initNewPerson();
			return "person-list";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("You are not allowed to create person"));
			return "person-list";
		}
	}

	@PostConstruct
	public void initNewPerson() {
		newPerson = new Person();
	}
}
