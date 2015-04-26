package net.dubrouski.fams.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Entity
@Table(name = "`USER`", uniqueConstraints = { @UniqueConstraint(columnNames = "LOGIN") })
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "{user.validate.login.required}")
    @Length(min = 1, max = 255, message = "{user.validate.login.length}")
    @Column(name = "LOGIN")
    private String login;

    @NotBlank(message = "{user.validate.password.required}")
    @Length(min = 6, max = 255, message = "{user.validate.password.length}")
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER2USER_RIGHT", joinColumns = @JoinColumn(name = "USER_ID"), 
            inverseJoinColumns = @JoinColumn(name = "USER_RIGHT_ID"))
    private List<UserRight> userRights;
    
    @OneToOne(optional = true)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRight> getUserRights() {
        return userRights;
    }

    public void setUserRights(List<UserRight> userRights) {
        this.userRights = userRights;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User [id " + this.id + ", login " + this.login + "]";
    }
}
