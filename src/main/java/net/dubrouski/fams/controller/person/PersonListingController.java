package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import net.dubrouski.fams.annotations.LoggedIn;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.enums.UserRightIds;
import net.dubrouski.fams.service.PersonService;

@ManagedBean
@SessionScoped
public class PersonListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private PersonService personService;
	
	private LazyDataModel<Person> lazyPersons;

	// private List<Person> persons;

	// private String searchTerm;

	private long rowCount;
	private int currentPage;

	private int pageSize = 25;

	@PostConstruct
	public void loadPersons() {
		lazyPersons = new LazyDataModel<Person>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				logger.log(Level.INFO, "first:" + first + ", pageSize: "
						+ pageSize);

				List<Person> result = new ArrayList<Person>();
				result = personService.getPersonsByPage(pageSize, first);
				return result;
			}
		};

		// TODO resolve casting!
		lazyPersons.setRowCount((int) personService.getPersonsCount());
		lazyPersons.setPageSize(pageSize);
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	//
	// public void setSearchTerm(String searchTerm) {
	// this.searchTerm = searchTerm;
	// }
	//
	// public String getSearchTerm() {
	// return this.searchTerm;
	// }

	// public List<Person> getPersons() {
	// logger.info("getting persons");
	// return personService.listPersons();
	// }

	public long getRowCount() {
		return rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	// @PostConstruct
	// public void init() {
	// loadPersons();
	// }

	// public void handlePersonsRefresh() {
	// loadPersons();
	// }

	// public void handlePersonsSearch() {
	// this.persons = personService.searchByNames(searchTerm);
	// }

	public LazyDataModel<Person> getLazyPersons() {
		return lazyPersons;
	}

	// public void setLazyPersons(LazyDataModel<Person> lazyPersons) {
	// this.lazyPersons = lazyPersons;
	// }

	// private void loadPersons() {
	// this.persons = personService.listPersons();
	// }
}
