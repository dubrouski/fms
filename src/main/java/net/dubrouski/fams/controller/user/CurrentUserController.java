package net.dubrouski.fams.controller.user;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.controller.LocaleManager;
import net.dubrouski.fams.controller.LoginManager;
import net.dubrouski.fams.model.User;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class CurrentUserController {

	@Inject
	LoginManager loginManager;

	@Inject
	LocaleManager localeManager;
	
	@Inject
	CurrentUserHolder currentUserHolder;

	public User getCu() {
		return currentUserHolder.getLoggedUser();
	}

	public String getLanguage() {
		return localeManager.getLanguage();
	}

}
