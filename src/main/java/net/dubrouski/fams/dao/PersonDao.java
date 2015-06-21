package net.dubrouski.fams.dao;

import java.util.List;
import java.util.Set;

import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonDao extends BaseDao<Person, Long> {

	Person getByLegalId(String legalId);

	Person getPersonWithAddresses(Long id);

	List<Person> list(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, Set<SearchFilter> filters);

	long getCount(Set<SearchFilter> filters);
}
