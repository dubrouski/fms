package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.Country;

@Stateless
@Named(value = "countryDao")
public class CountryDaoImpl extends BaseDaoImpl<Country, Long> implements
		CountryDao {

}
