package net.dubrouski.fams.dao;

import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

import java.util.List;
import java.util.Set;

/**
 * Created by tmarton on 5/2/15.
 */
public interface ContractDao extends BaseDao<Contract, Long> {

	public List<Contract> getContractsByPerson(Person person);

	public List<Contract> getContractsByAccommodationUnit(
			AccommodationUnit accommodationUnit);

	public List<Contract> list(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, Set<SearchFilter> filters);

	public long getCount(Set<SearchFilter> filters);

	Contract getContractWithMetersData(Long id);

}
