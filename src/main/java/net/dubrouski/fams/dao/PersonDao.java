package net.dubrouski.fams.dao;

import java.util.List;
import java.util.Map;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonDao extends BaseDao<Person, Long> {

	Person getByLegalId(String legalId);

	Person getPersonWithAddresses(Long id);

	List<Person> listPersons(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, String searchTerm);

	long getPersonsCount();
}
