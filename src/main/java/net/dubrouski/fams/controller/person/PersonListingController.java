package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

@ManagedBean
@SessionScoped
public class PersonListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private PersonService personService;

	private List<Person> persons;

	private String searchTerm;

	private int rowCount;
	private int currentPage;

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getSearchTerm() {
		return this.searchTerm;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	@PostConstruct
	public void init() {
		loadPersons();
	}

	public void handlePersonsRefresh() {
		loadPersons();
	}

	public void handlePersonsSearch() {
		this.persons = personService.searchByNames(searchTerm);
	}

	private void loadPersons() {
		this.persons = personService.listPersons();
	}
}
