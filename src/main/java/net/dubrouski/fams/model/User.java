package net.dubrouski.fams.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import net.dubrouski.fams.model.enums.UserRoles;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Entity
@Table(name = "FMS_USER", uniqueConstraints = { @UniqueConstraint(columnNames = "USERNAME") })
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "{user.validate.login.required}")
    @Length(min = 1, max = 255, message = "{user.validate.login.length}")
    @Column(name = "USERNAME")
    private String username;

    @NotBlank(message = "{user.validate.password.required}")
    @Length(min = 6, max = 255, message = "{user.validate.password.length}")
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FMS_USER2USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), 
            inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID"))
    private List<UserRole> userRoles;
    
    @OneToOne(optional = true)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    
    @Transient
    private Set<UserRoles> userRolesSet = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<UserRoles> getUserRightsSet() {
        if (userRolesSet == null) {
        	userRolesSet = new HashSet<>();
            if (userRoles != null) {
                for (UserRole userRight : userRoles) {
                	userRolesSet.add(userRight.getRightName());
                }
            }
        }
        
        return userRolesSet;
    }

    public List<UserRole> getUserRights() {
        return userRoles;
    }

    public void setUserRights(List<UserRole> userRights) {
        this.userRoles = userRights;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public boolean hasRight(UserRoles right) {
        return getUserRightsSet().contains(right);
    }

    @Override
    public String toString() {
        return "User [id " + this.id + ", login " + this.username + "]";
    }
}
