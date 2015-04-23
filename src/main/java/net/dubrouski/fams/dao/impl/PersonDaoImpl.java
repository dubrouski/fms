package net.dubrouski.fams.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

	@Inject
	Logger logger;

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

	@Override
	public Person getPersonWithAddresses(Long id) {
		logger.info("getPersonWithAddresses(): Id to find person: " + id);
		if (id == null) {
			throw new IllegalArgumentException("id is null.");
		}
		TypedQuery<Person> query = this.entityManager
				.createQuery(
						"select p from Person p left join fetch p.addresses where p.id = :id",
						Person.class);
		try {
			return query.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			logger.info("No person found with id " + id);
			return null;
		}
	}

	@Override
	public List<Person> searchByNames(String searchTerm) {
		if (searchTerm == null) {
			throw new IllegalArgumentException("searchTerm is null.");
		}
		TypedQuery<Person> query = this.entityManager
				.createQuery(
						"select p from Person p where upper(p.firstName) like CONCAT('%', :searchTerm, '%')  "
								+ "or upper(p.lastName) like  CONCAT('%', :searchTerm, '%')",
						Person.class);

		return query.setParameter("searchTerm", searchTerm.toUpperCase())
				.getResultList();
	}

	@Override
	public List<Person> getPersonsByPage(int pageSize, int first) {
		TypedQuery<Person> query = this.entityManager
				.createQuery("select p from Person p", Person.class)
				.setFirstResult(first).setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public long getPersonsCount() {
		TypedQuery<Long> query = this.entityManager.createQuery(
				"select count(p.id) from Person p", Long.class);

		return query.getSingleResult();
	}

}
