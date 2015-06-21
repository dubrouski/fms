package net.dubrouski.fams.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

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
	public List<Person> listPersons(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, String searchTerm) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
		Root<Person> personRoot = criteriaQuery.from(Person.class);

		// TODO replace literal with metamodel
		// Metamodel meta = this.entityManager.getMetamodel();
		// EntityType<Person> Person_ = meta.entity(Person.class);

		Path<Object> path = personRoot.get(sortField == null ? "code"
				: sortField);

		criteriaQuery.select(personRoot);
		if (searchTerm != null && !searchTerm.isEmpty()) {
			Predicate orClause = builder.or(builder.like(
					builder.lower(personRoot.<String> get("firstName")), "%"
							+ searchTerm.toLowerCase() + "%"), builder.like(
					builder.lower(personRoot.<String> get("lastName")), "%"
							+ searchTerm.toLowerCase() + "%"));

			criteriaQuery.where(orClause);
		}

		if (sortingOrder.equals(SortingOrder.ASCENDING)) {
			criteriaQuery.orderBy(builder.asc(path));
		} else {
			criteriaQuery.orderBy(builder.desc(path));
		}
		TypedQuery<Person> query = this.entityManager
				.createQuery(criteriaQuery).setFirstResult(first)
				.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public long getPersonsCount(String searchTerm) {

		CriteriaBuilder qb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		Root<Person> personRoot = cq.from(Person.class);
		cq.select(qb.count(personRoot));
		if (searchTerm != null && !searchTerm.isEmpty()) {
			Predicate orClause = qb.or(
					qb.like(qb.lower(personRoot.<String> get("firstName")), "%"
							+ searchTerm.toLowerCase() + "%"),
					qb.like(qb.lower(personRoot.<String> get("lastName")), "%"
							+ searchTerm.toLowerCase() + "%"));

			cq.where(orClause);
		}
		long result = entityManager.createQuery(cq).getSingleResult();
		logger.info("Found " + result + " entities for searchTerm: " + searchTerm);
		return result;
	}

}
