package net.dubrouski.fams.controller.user;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.controller.ControllerHelper;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;
import net.dubrouski.fams.service.UserService;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateful
@Model
public class UserCreationController {

	@Inject
	Logger logger;

	@Inject
	UserService userService;

	private User newUser;

	private List<UserRole> availableRoles;

	public User getNewUser() {
		return newUser;
	}

	public List<UserRole> getUserRoles() {
		return this.availableRoles;
	}

	public String createUser() {
		logger.info("Saving new user: " + newUser.toString());

		userService.saveUser(newUser);

		ControllerHelper.addInfoMessage(
				FacesMessage.SEVERITY_INFO,
				String.format("User %s create successfully",
						newUser.getUsername()), true);

		initNewUser();
		return "list?faces-redirect=true";
	}

	@PostConstruct
	public void initNewUser() {
		newUser = new User();
		this.availableRoles = userService.listUserRoles();
	}
}
