package net.dubrouski.fams.controller;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import net.dubrouski.fams.annotations.LoggedIn;
import net.dubrouski.fams.model.Credentials;
import net.dubrouski.fams.model.User;
import net.dubrouski.fams.service.UserService;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@SessionScoped 
@ManagedBean
public class LoginManager implements Serializable {

   @Inject Credentials credentials;
   @Inject UserService userService;

   private User user;

   public void login() {
      User userLogin = userService.loginUser(credentials.getUsername(), credentials.getPassword());

      if (userLogin != null) {
         // login ok
          user = userLogin;
      }
      else {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login failed."));
      }
   }
   
   public void logout() {
      user = null;
   }
    
   public boolean isLoggedIn() {
      return user != null;
   }
    
   @Produces @LoggedIn
   public User getCurrentUser() {
      return user;
   }
}