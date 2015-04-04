package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonUpdateController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	private Person personToUpdate;

	@Produces
	public Person getPersonToUpdate() {
		return personToUpdate;
	}

	public String startPersonUpdate(Long id) {

		this.personToUpdate = personService.getPersonById(id);

		return "person-update";
	}

	@Named
	public String updatePerson(@Valid Person person) {
		personService.updatePerson(person);
		return "person-list";
	}

}
