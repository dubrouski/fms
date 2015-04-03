package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.Country;

@Stateless
@Named(value = "countryDao")
public class CountryDaoImpl extends BaseDaoImpl<Country, Long> implements
		CountryDao {

	@Override
	public Country getCountryByCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("given code is null.");
		}
		TypedQuery<Country> query = this.entityManager.createQuery(
				"select c from Country c where c.code = :code", Country.class);
		try {
			return query.setParameter("code", code).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
