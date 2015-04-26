package net.dubrouski.fams.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Named
@RequestScoped
public class Credentials {

    private String username;

    private String password;

    @NotNull
    @Length(min = 3, max = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 3, max = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
