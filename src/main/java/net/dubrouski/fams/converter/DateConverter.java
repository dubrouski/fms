package net.dubrouski.fams.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * @author stanislau.dubrouski
 * Is used to convert values between String and LocalDate representations
 * when sending them to JSF page and getting user input from the forms.
 */
@FacesConverter("dateStringConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate result;
		try {
			result = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			FacesMessage msg = new FacesMessage("String '" + date
					+ "' was not understood as valid date.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}

		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value.toString();
	}

}
