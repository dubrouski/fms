package net.dubrouski.fams.service.impl;

import java.security.MessageDigest;
import java.security.Security;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.UserDao;
import net.dubrouski.fams.dao.UserRoleDao;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.UserRole;
import net.dubrouski.fams.model.enums.UserRoles;
import net.dubrouski.fams.service.UserService;
import net.dubrouski.fams.util.SHA512;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Named(value = "userService")
public class UserServiceImpl implements UserService {
	@Inject
	UserDao userDao;
	
	@Inject
	UserRoleDao rolesDao;

	@Inject
	Logger logger;

	@Override
	public void saveUser(User user) {

		user.setPassword(SHA512.hashText(user.getPassword()));
		userDao.save(user);
		logger.log(Level.INFO, "Created new user: {0}", user.toString());
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		logger.log(Level.INFO, "Requested deletion of user: {0}",
				user.toString());
		userDao.delete(user);
		logger.log(Level.INFO, "Deleted user: {0}", user.toString());
	}

	@Override
	public List<User> listUsers() {
		return userDao.listAll();
	}

	@Override
	public List<UserRole> listUserRoles() {
		return rolesDao.listAll();
	}

	@Override
	public UserRole getUserRoleById(Long id) {
		return rolesDao.getByID(id);
	}

	@Override
	public UserRole getUserRoleByType(UserRoles type) {
		return rolesDao.getByType(type);
	}	
}
