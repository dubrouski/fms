package net.dubrouski.fams.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.service.PersonService;

@Named(value = "idToAccommConverter")
@FacesConverter("idToAccommConverter")
public class AccommIdConverter implements Converter {

	@Inject
	AccommodationUnitService aService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String code) {
		return aService.getAccommodationById(Long.valueOf(code));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1,
			Object accomm) {
		return accomm.toString();
	}

}
