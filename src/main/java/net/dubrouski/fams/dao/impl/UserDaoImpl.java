package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.dubrouski.fams.dao.UserDao;
import net.dubrouski.fams.model.User;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Stateless
@Named(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User getByLoginAndPassword(String login, String password) {
        if (login == null || password == null) {
            throw new IllegalArgumentException("Login or password is null.");
        }
        
        TypedQuery<User> query = this.entityManager.createQuery(
                "select u from User u where u.login = :login and u.password = :password",
                User.class);
        
        try {
            return query.setParameter("login", login).setParameter("password", password).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
