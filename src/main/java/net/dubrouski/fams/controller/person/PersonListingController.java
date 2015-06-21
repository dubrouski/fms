package net.dubrouski.fams.controller.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;
import net.dubrouski.fams.service.PersonService;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean
@SessionScoped
public class PersonListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private PersonService personService;

	private LazyDataModel<Person> lazyPersons;

	private long rowCount;
	private int currentPage;

	private int pageSize = 25;

	private String searchTerm;

	@PostConstruct
	public void loadPersons() {
		lazyPersons = new LazyDataModel<Person>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				List<Person> result = new ArrayList<Person>();
				result = personService.listPersons(pageSize, first, sortField,
						SortingOrder.valueOf(sortOrder.name()), searchTerm);
				return result;
			}

			@Override
			public int getRowCount() {
				return (int) personService.getPersonsCount(searchTerm);
			}
		};

		lazyPersons.setPageSize(pageSize);
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getRowCount() {
		return rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public LazyDataModel<Person> getLazyPersons() {
		return lazyPersons;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

}
