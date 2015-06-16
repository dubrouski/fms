package net.dubrouski.fams.service;

import java.util.List;

import net.dubrouski.fams.model.User;
import net.dubrouski.fams.model.UserRole;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
public interface UserService {
    
    public void saveUser(User user);
    
    public void updateUser(User user);
    
    public User getUserByUsername(String username);
    
    public void deleteUser(User user);
    
    public List<User> listUsers();
    
    public List<UserRole> listUserRoles();
    
    public UserRole getUserRoleById(Long id);
    
    //public User loginUser(String login, String password);
    
}
