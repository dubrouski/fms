package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.service.PersonService;

@ManagedBean
@RequestScoped
public class AccListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private AccommodationUnitService accommodationService;

	@Inject
	private PersonService personService;

	private LazyDataModel<AccommodationUnit> lazyUnits;

	private LazyDataModel<Person> lazyPersons;
	
	private long rowCount;
	private int currentPage;

	private int pageSize = 25;

	@PostConstruct
	public void loadUnits() {
		logger.log(Level.INFO, "Post construct called.");
		lazyUnits = new LazyDataModel<AccommodationUnit>() {

			private static final long serialVersionUID = 1L;


			@Override
			public List<AccommodationUnit> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				List<AccommodationUnit> result = new ArrayList<AccommodationUnit>();
//				result = accommodationService.getAccommodationsByPage(pageSize,
//						first);
				result = accommodationService.listAccommodations();
				return result;
			}
		};
		
		lazyPersons = new LazyDataModel<Person>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Person> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				List<Person> result = new ArrayList<Person>();
				//result = personService.getPersonsByPage(pageSize, first);
				result = personService.listPersons();
				return result;
			}
		};

		// TODO resolve casting!
		lazyPersons.setRowCount((int) personService.getPersonsCount());
		lazyPersons.setPageSize(pageSize);

		lazyUnits.setRowCount(25);
		lazyUnits.setPageSize(pageSize);
	}

//	@PostConstruct
//	public void loadPersons() {
//		lazyPersons = new LazyDataModel<Person>() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public List<Person> load(int first, int pageSize, String sortField,
//					SortOrder sortOrder, Map<String, Object> filters) {
//
//				List<Person> result = new ArrayList<Person>();
//				result = personService.getPersonsByPage(pageSize, first);
//				return result;
//			}
//		};
//
//		// TODO resolve casting!
//		lazyPersons.setRowCount((int) personService.getPersonsCount());
//		lazyPersons.setPageSize(pageSize);
//	}
	
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

	public LazyDataModel<AccommodationUnit> getLazyUnits() {
		return lazyUnits;
	}
	
	public LazyDataModel<Person> getLazyPersons() {
		return lazyPersons;
	}
}
