package net.dubrouski.fams.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class LocaleManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot()
			.getLocale();

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.toString();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

}
