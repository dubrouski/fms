package net.dubrouski.fams.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.Country;

@Named
public class CountryCodeConverter implements Converter {

	@Inject
	CountryDao countryDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String code) {
		return countryDao.getCountryByCode(code);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1,
			Object country) {
		return country.toString();
	}

}
