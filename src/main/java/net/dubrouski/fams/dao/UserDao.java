package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.User;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
public interface UserDao extends BaseDao<User, Long>{

    public User getByLoginAndPassword(String login, String password);
    
    public User getByUsername(String username);
    
}
