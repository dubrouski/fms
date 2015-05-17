package net.dubrouski.fams.dao.impl;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.SortingOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Logger;

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
	public List<Contract> listContracts(int pageSize, int first,
			String sortField, SortingOrder sortingOrder, String searchCode) {

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
		if (searchCode != null && !searchCode.isEmpty()) {
			Predicate codeEquals =

			builder.equal(contractRoot.<Long> get("code"),
					Long.valueOf(searchCode));

			criteriaQuery.where(codeEquals);
		}

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
	public long getContractsCount() {
		TypedQuery<Long> query = this.entityManager.createQuery(
				"select count(c.id) from Contract c", Long.class);

		return query.getSingleResult();
	}
}
