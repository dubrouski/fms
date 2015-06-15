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
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean
@RequestScoped
public class AccommodationListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private AccommodationUnitService accommodationService;

	private LazyDataModel<AccommodationUnit> lazyUnits;
	private List<AccommodationUnit> eagerUnits;

	private long rowCount;
	private int currentPage;

	private int pageSize = 25;

	@PostConstruct
	public void loadUnits() {
		logger.log(Level.INFO, "Post construct called.");
		eagerUnits = accommodationService.listAccommodations();
		lazyUnits = new LazyDataModel<AccommodationUnit>() {

			private static final long serialVersionUID = 1L;

			@Inject
			private Logger logger;

			@Override
			public List<AccommodationUnit> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				List<AccommodationUnit> result = new ArrayList<AccommodationUnit>();
				result = accommodationService.listAccommodationsByType("room");
				logger.log(Level.INFO,
						"Accommodations retrieved:" + result.size());
				rowCount = result.size();
				return result;
			}
		};

		lazyUnits.setRowCount((int) rowCount);
		lazyUnits.setPageSize(pageSize);
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

	public LazyDataModel<AccommodationUnit> getLazyUnits() {
		return lazyUnits;
	}

	public List<AccommodationUnit> getEagerUnits() {
		return eagerUnits;
	}
}