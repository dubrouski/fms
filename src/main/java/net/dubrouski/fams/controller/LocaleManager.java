package net.dubrouski.fams.controller;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class LocaleManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale;
	
	@Inject
	Logger logger;

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.toString();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		logger.info("Locale changed to " + locale.toString());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	@PostConstruct
	public void setDefaultLocale() {
		this.locale = new Locale("en");
		
//		this.locale = FacesContext.getCurrentInstance().getViewRoot()
//				.getLocale();
	}
}
