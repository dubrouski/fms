package net.dubrouski.fams.controller.person;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.annotations.LoggedIn;
import net.dubrouski.fams.controller.user.CurrentUserHolder;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.enums.UserRightIds;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonDeletionController {

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	@Inject
	CurrentUserHolder currentUserHolder;

	private Person personForDeletion;

	@Produces
	@Named
	public Person getPersonForDeletion() {
		return personForDeletion;
	}

	public String requestPersonDelete(Person person) {

		this.personForDeletion = person;
		if (personForDeletion == null) {
			logger.log(Level.INFO,
					"requestPersonDelete: personForDeletion is null.");
		}
		return "person-confirm-delete";
	}

	public String confirmPersonDelete() {

		if (personForDeletion == null) {
			logger.log(Level.INFO,
					"confirmPersonDelete: personForDeletion is null.");
		}
		personService.delete(personForDeletion);
		return "person-list";
	}
}
