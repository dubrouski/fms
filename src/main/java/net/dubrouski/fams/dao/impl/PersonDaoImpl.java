package net.dubrouski.fams.dao.impl;

import java.util.List;
import java.util.Set;
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
import net.dubrouski.fams.filter.PersonNamesFilter;
import net.dubrouski.fams.filter.SearchFilter;
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
	public List<Person> list(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, Set<SearchFilter> filters) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
		Root<Person> personRoot = criteriaQuery.from(Person.class);

		// TODO replace literal with metamodel
		// Metamodel meta = this.entityManager.getMetamodel();
		// EntityType<Person> Person_ = meta.entity(Person.class);

		Path<Object> path = personRoot.get(sortField == null ? "code"
				: sortField);

		criteriaQuery.select(personRoot);

		applyFilters(builder, criteriaQuery, personRoot, filters);

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
	public long getCount(Set<SearchFilter> filters) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Person> personRoot = query.from(Person.class);
		query.select(builder.count(personRoot));

		applyFilters(builder, query, personRoot, filters);

		long result = entityManager.createQuery(query).getSingleResult();
		logger.info("Found " + result + " entities for search: " + filters);
		return result;
	}

	@SuppressWarnings("rawtypes")
	private CriteriaQuery applyFilters(CriteriaBuilder builder,
			CriteriaQuery query, Root<Person> personRoot,
			Set<SearchFilter> filters) {
		for (SearchFilter filter : filters) {
			if (filter instanceof PersonNamesFilter) {
				query = applySearchByNames(builder, query, personRoot,
						(PersonNamesFilter) filter);
			}
		}

		return query;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	CriteriaQuery applySearchByNames(CriteriaBuilder builder,
			CriteriaQuery query, Root<Person> personRoot,
			PersonNamesFilter filter) {
		if (filter.getSearchTerm().isEmpty()) {
			return query;
		}
		Predicate orClause = builder.or(builder.like(
				builder.lower(personRoot.<String> get("firstName")), "%"
						+ filter.getSearchTerm().toLowerCase() + "%"), builder
				.like(builder.lower(personRoot.<String> get("lastName")), "%"
						+ filter.getSearchTerm().toLowerCase() + "%"));

		query.where(orClause);

		return query;
	}
}
