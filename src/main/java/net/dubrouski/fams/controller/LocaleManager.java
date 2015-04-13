package net.dubrouski.fams.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
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

	private Locale locale;

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

	@PostConstruct
	public void setDefaultLocale() {
		this.locale = new Locale("en_us");
		
//		this.locale = FacesContext.getCurrentInstance().getViewRoot()
//				.getLocale();
	}
}
