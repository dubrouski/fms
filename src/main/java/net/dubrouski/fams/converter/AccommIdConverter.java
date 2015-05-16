package net.dubrouski.fams.converter;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

@ManagedBean(name = "accommIdConverterBean")
@FacesConverter(value = "accommIdConverter")
public class AccommIdConverter implements Converter {

	@Inject
	AccommodationUnitService accService;

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
			AccommodationUnit result = accService.getAccommodationById(id);

			System.out.println("found entity " + result.toString());
			return result;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Could not convert given id to long", e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1,
			Object value) {
		if (value == null || value.toString().isEmpty()
				|| !(value instanceof AccommodationUnit))
			return "";

		return String.valueOf(((AccommodationUnit) value).getId());
	}
}