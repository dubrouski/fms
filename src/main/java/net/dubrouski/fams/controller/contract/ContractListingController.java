package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.enums.SortingOrder;
import net.dubrouski.fams.service.ContractService;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean
@SessionScoped
public class ContractListingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private ContractService contractService;

	private LazyDataModel<Contract> lazyContracts;

	private long rowCount;
	private int currentPage;

	private int pageSize = 25;

	private String searchTerm;

	@PostConstruct
	public void loadContract() {
		lazyContracts = new LazyDataModel<Contract>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Contract> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				List<Contract> result = new ArrayList<Contract>();
				result = contractService.listContracts(pageSize, first,
						sortField, SortingOrder.valueOf(sortOrder.name()),
						searchTerm);
				return result;
			}
		};

		// TODO resolve rowscount setting.
		lazyContracts.setRowCount((int) contractService.getContractsCount());
		lazyContracts.setPageSize(pageSize);
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

	public LazyDataModel<Contract> getLazyContracts() {
		return lazyContracts;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
