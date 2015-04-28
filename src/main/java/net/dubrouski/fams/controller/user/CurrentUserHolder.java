package net.dubrouski.fams.controller.user;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.dubrouski.fams.model.User;

import java.io.Serializable;

@SessionScoped
@Named(value = "currentUserHolder")
public class CurrentUserHolder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User loggedUser;

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

}
