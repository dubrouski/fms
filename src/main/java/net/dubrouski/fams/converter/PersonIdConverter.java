package net.dubrouski.fams.converter;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

@ManagedBean(name = "personIdConverterBean")
@FacesConverter(value = "personIdConverter")
public class PersonIdConverter implements Converter {

	@Inject
	PersonService pService;

	@Inject
	Logger log;

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String value) {
		if (value.isEmpty()) {
			return null;
		}
		try {
			Long id = Long.parseLong(value);
			Person result = pService.getPersonById(id);

//			log.info(msg);
			
			System.out.println("found entity " + result.toString());
			return result;
		} catch (NumberFormatException e) {
			return new Person();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1,
			Object value) {
		if (value == null || value.toString().isEmpty()
				|| !(value instanceof Person))
			return "";

		return String.valueOf(((Person) value).getId());
	}
}
