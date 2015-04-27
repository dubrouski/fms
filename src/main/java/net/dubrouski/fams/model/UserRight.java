package net.dubrouski.fams.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import net.dubrouski.fams.model.enums.UserRightIds;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Entity
@Table(name = "USER_RIGHT")
public class UserRight implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private UserRightIds id;

    @Column(name = "RIGHT_NAME")
    private String rightName;

    public UserRightIds getId() {
        return id;
    }

    public void setId(UserRightIds id) {
        this.id = id;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Override
    public String toString() {
        return "Right [id " + this.id + ", name " + this.rightName + "]";
    }
}
