package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateless
@Named(value = "personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person, Long> implements
		PersonDao {

	@Override
	public Person getByLegalId(String legalId) {
		if (legalId == null) {
			throw new IllegalArgumentException("legalId is null.");
		}
		TypedQuery<Person> query = this.entityManager.createQuery(
				"select p from Person p where p.legalId = :legalId",
				Person.class);
		try {
			return query.setParameter("legalId", legalId).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
}
