package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonDao extends BaseDao<Person, Long> {

	public Person getByLegalId(String legalId);
}
