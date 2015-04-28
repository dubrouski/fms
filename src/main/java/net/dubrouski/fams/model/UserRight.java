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
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "RIGHT_NAME")
	@Enumerated(EnumType.STRING)
	private UserRightIds rightName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRightIds getRightName() {
		return rightName;
	}

	public void setRightName(UserRightIds rightName) {
		this.rightName = rightName;
	}

	@Override
	public String toString() {
		return "Right [id " + this.id + ", name " + this.rightName + "]";
	}
}
