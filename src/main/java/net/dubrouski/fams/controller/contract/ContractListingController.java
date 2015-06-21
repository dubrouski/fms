package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.filter.ContractCodeFilter;
import net.dubrouski.fams.filter.ContractStatesFilter;
import net.dubrouski.fams.filter.PersonNamesFilter;
import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.enums.ContractState;
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

	private Long contractCodeToSearch;
	private ContractState[] contractStatesToSearch;

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
						buildFilters());
				return result;
			}

			@Override
			public int getRowCount() {
				return (int) contractService.getContractsCount(buildFilters());
			}
		};
		lazyContracts.setPageSize(pageSize);
	}

	private Set<SearchFilter> buildFilters() {
		Set<SearchFilter> filtersSet = new HashSet<SearchFilter>();
		ContractCodeFilter contractCodeFilter = new ContractCodeFilter(
				contractCodeToSearch);
		filtersSet.add(contractCodeFilter);
		ContractStatesFilter statesFilter = new ContractStatesFilter(
				contractStatesToSearch);
		filtersSet.add(statesFilter);

		return filtersSet;
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

	public Long getContractCode() {
		return contractCodeToSearch;
	}

	public void setContractCode(Long cd) {
		this.contractCodeToSearch = cd;
	}

	public ContractState[] getContractStatesToSearch() {
		return contractStatesToSearch;
	}

	public void setContractStatesToSearch(ContractState[] contractStatesToSearch) {
		this.contractStatesToSearch = contractStatesToSearch;
	}

	public ContractState[] getAvailableStates() {
		return ContractState.values();
	}
}
