package net.dubrouski.fams.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.dubrouski.fams.model.enums.UserRoles;

/**
 *
 * @author Martin Jelínek (xjeline5)
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ROLE_NAME")
	@Enumerated(EnumType.STRING)
	private UserRoles roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRoles getRightName() {
		return roleName;
	}

	public void setRightName(UserRoles rightName) {
		this.roleName = rightName;
	}

	@Override
	public String toString() {
		return "Right [id " + this.id + ", name " + this.roleName + "]";
	}
}
