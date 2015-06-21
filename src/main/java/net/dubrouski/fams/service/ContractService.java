package net.dubrouski.fams.service;

import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.enums.SortingOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by tmarton on 5/2/15.
 */
public interface ContractService {

	public Contract getContractById(Long id);

	public void saveContract(Contract contract, Price price);

	public void updateContract(Contract contract);

	public void deleteContract(Contract contract);

	public List<Contract> listContracts();

	public List<Contract> getContractsByPerson(Person person);

	public List<Contract> getContractsByAccommodationUnit(
			AccommodationUnit accommodationUnit);

	public List<Contract> listContracts(int pageSize, int first,
			String sortField, SortingOrder valueOf, Set<SearchFilter> filters);

	public long getContractsCount(Set<SearchFilter> filters);

	public boolean handoverKeys(Contract contract);

	public boolean signContract(Contract contract);

	public boolean cancelContract(Contract contract);

	public boolean closeContract(Contract contract);

	public boolean createTerminationRequest(Contract contract);

}
