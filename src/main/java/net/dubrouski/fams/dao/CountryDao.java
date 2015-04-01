package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.Country;

/**
 * @author stanislau.dubrouski
 *
 */
public interface CountryDao extends BaseDao<Country, Long> {
	public Country getCountryByCode(String code);
}
