package net.dubrouski.fams.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class ControllerHelper {

	public static void addInfoMessage(Severity severity, String message,
			boolean keepAfterRedirect) {

		FacesMessage msg = new FacesMessage(severity, message, message);

		FacesContext.getCurrentInstance().addMessage(null, msg);

		if (keepAfterRedirect) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
		}
	}
}
