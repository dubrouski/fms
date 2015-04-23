package net.dubrouski.fams.dao;

import java.util.List;

import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonDao extends BaseDao<Person, Long> {

	Person getByLegalId(String legalId);

	Person getPersonWithAddresses(Long id);

	List<Person> searchByNames(String searchTerm);
}
