package net.dubrouski.fams.controller.login;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.service.UserService;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@SessionScoped
@ManagedBean
public class LoginManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	JAASService jService;

	@Inject
	UserService userService;

	@Inject
	Logger logger;

	public String logout() {
		HttpServletRequest origRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		origRequest.getSession().invalidate();
		
		return "index?faces-redirect=true";
	}

	public boolean isLoggedIn() {
		return getCurrentUser() != null;
	}

	public Principal getCurrentUser() {
		return jService.getCurrentUser();
	}

	public Person getAssociatedPerson() {
		logger.info("Getting assoc. person for user "
				+ jService.getCurrentUser().getName());
		Person result = userService.getUserByUsername(
				getCurrentUser().getName()).getPerson();

		logger.info("Found person " + result);

		return result;
	}

	public boolean isLoggedUserIsClient() {
		User u = userService.getUserByUsername(getCurrentUser().getName());
		if (u == null) {
			return false;
		} else
			return u.getPerson() != null;
	}
}
