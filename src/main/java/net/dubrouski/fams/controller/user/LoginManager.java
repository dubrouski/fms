package net.dubrouski.fams.controller.user;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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

	public void logout() {
		HttpServletRequest origRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		origRequest.getSession().invalidate();
	}

	public boolean isLoggedIn() {
		return getCurrentUser() != null;
	}

	public Principal getCurrentUser() {
		return jService.getCurrentUser();
	}
}
