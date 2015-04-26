package net.dubrouski.fams.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import net.dubrouski.fams.dao.UserDao;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.service.UserService;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Named(value = "userService")
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    @Inject
    Logger logger;

    @Override
    public void saveUser(User user) {
        userDao.save(user);
        logger.log(Level.INFO, "Created new user: {0}", user.toString());
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getByID(id);
    }

    @Override
    public void deleteUser(User user) {
        logger.log(Level.INFO, "Requested deletion of user: {0}", user.toString());
        userDao.delete(user);
        logger.log(Level.INFO, "Deleted user: {0}", user.toString());
    }

    @Override
    public List<User> listUsers() {
        return userDao.listAll();
    }

    @Override
    public User loginUser(String login, String password) {
        return userDao.getByLoginAndPassword(login, password);
    }
}
