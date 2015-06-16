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

	public UserRoles getRoleName() {
		return roleName;
	}

	public void setRoleName(UserRoles roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Right [id " + this.id + ", name " + this.roleName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

}

