package net.dubrouski.fams.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

@Named(value = "idToPersonConverter")
@FacesConverter("idToPersonConverter")
public class PersonIdConverter implements Converter {

	@Inject
	PersonService pService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String code) {
		return pService.getPersonById(Long.valueOf(code));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1,
			Object person) {
		return person.toString();
	}
}
