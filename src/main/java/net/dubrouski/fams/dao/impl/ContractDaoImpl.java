package net.dubrouski.fams.dao.impl;

import java.util.Arrays;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.filter.ContractCodeFilter;
import net.dubrouski.fams.filter.ContractStatesFilter;
import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

/**
 * Created by tmarton on 5/2/15.
 */
@Stateless
@Named(value = "contractDao")
public class ContractDaoImpl extends
		BaseDaoImpl<net.dubrouski.fams.model.Contract, Long> implements
		ContractDao {

	@Inject
	Logger logger;

	public List<Contract> getContractsByPerson(Person person) {
		if (person == null) {
			throw new FmsException("Person cannot be null.");
		}

		TypedQuery<Contract> query = this.entityManager
				.createQuery(
						"select c from Contract c join c.tenant tenant where tenant.id = :id",
						Contract.class);
		return query.setParameter("id", person.getId()).getResultList();
	}

	public List<Contract> getContractsByAccommodationUnit(
			AccommodationUnit accommodationUnit) {
		if (accommodationUnit == null) {
			throw new FmsException("Address cannot be null.");
		}

		TypedQuery<Contract> query = this.entityManager
				.createQuery(
						"select c from Contract c join c.accommodationUnit au where au.id = :id",
						Contract.class);
		return query.setParameter("id", accommodationUnit.getId())
				.getResultList();
	}

	@Override
	public List<Contract> list(int pageSize, int first, String sortField,
			SortingOrder sortingOrder, Set<SearchFilter> filters) {

		logger.info("start contracts list preparation...");

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Contract> criteriaQuery = builder
				.createQuery(Contract.class);
		Root<Contract> contractRoot = criteriaQuery.from(Contract.class);

		// TODO replace literal with metamodel
		// Metamodel meta = this.entityManager.getMetamodel();
		// EntityType<Person> Person_ = meta.entity(Person.class);

		logger.info("setting sorting field...");

		Path<Object> path = contractRoot.get(sortField == null ? "code"
				: sortField);

		logger.info("prepare select query...");
		criteriaQuery.select(contractRoot);

		applyFilters(builder, criteriaQuery, contractRoot, filters);

		logger.info("set sorting ordering...");

		if (sortingOrder.equals(SortingOrder.ASCENDING)) {
			criteriaQuery.orderBy(builder.asc(path));
		} else {
			criteriaQuery.orderBy(builder.desc(path));
		}

		logger.info("create query to execute...");
		TypedQuery<Contract> query = this.entityManager
				.createQuery(criteriaQuery).setFirstResult(first)
				.setMaxResults(pageSize);

		logger.info("ready to execute query...");
		List<Contract> result = query.getResultList();

		logger.info("query executed, found " + result.size()
				+ " items. return.");
		return result;
	}

	@Override
	public long getCount(Set<SearchFilter> filters) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Contract> contractRoot = query.from(Contract.class);
		query.select(builder.count(contractRoot));

		applyFilters(builder, query, contractRoot, filters);

		long result = entityManager.createQuery(query).getSingleResult();
		logger.info("Found " + result + " entities for search: " + filters);
		return result;
	}

	@Override
	public Contract getContractWithMetersData(Long id) {
		logger.info("getContractWithMetersData(): Id to find contract: " + id);
		if (id == null) {
			throw new IllegalArgumentException("id is null.");
		}
		TypedQuery<Contract> query = this.entityManager
				.createQuery(
						"select c from Contract c left join fetch c.startData left join fetch c.endData where c.id = :id",
						Contract.class);
		try {
			return query.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			logger.info("No contract found with id " + id);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private CriteriaQuery applyFilters(CriteriaBuilder builder,
			CriteriaQuery query, Root<Contract> contractRoot,
			Set<SearchFilter> filters) {
		for (SearchFilter filter : filters) {
			if (filter instanceof ContractCodeFilter) {
				query = applySearchByCode(builder, query, contractRoot,
						(ContractCodeFilter) filter);
			}
			if (filter instanceof ContractStatesFilter) {
				query = applySearchByStates(builder, query, contractRoot,
						(ContractStatesFilter) filter);
			}
		}

		return query;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	CriteriaQuery applySearchByStates(CriteriaBuilder builder,
			CriteriaQuery query, Root<Contract> contractRoot,
			ContractStatesFilter filter) {
		if (filter.getStates() == null || filter.getStates().length == 0) {
			return query;
		}
		Expression<String> exp = contractRoot.get("state");
		Predicate stateIn = exp.in(Arrays.asList(filter.getStates()));
		query.where(stateIn);
		return query;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	CriteriaQuery applySearchByCode(CriteriaBuilder builder,
			CriteriaQuery query, Root<Contract> contractRoot,
			ContractCodeFilter filter) {
		if (filter.getCode() == null) {
			return query;
		}
		Predicate codeEquals = builder.equal(contractRoot.<Long> get("code"),
				filter.getCode());

		query.where(codeEquals);

		return query;
	}
}
