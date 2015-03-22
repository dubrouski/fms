package net.dubrouski.fams.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author stanislau.dubrouski
 * 
 */
@Entity
@Table(name = "PERSON")
public class Person {

	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@NotEmpty
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotNull
	@NotEmpty
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@NotEmpty
	@Column(name = "OTHER_NAMES")
	private String otherNames;

	@NotNull
	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;

	@Column(name = "LEGAL_IDENTIFICATOR")
	private String legalId;

	@Column(name = "BUSINESS_ID", updatable = false)
	@GeneratedValue
	private Long businessId;

	@Column(name = "EMAIL")
	@Email
	private String email;

	@Column(name = "PHONE")
	@NotNull
	@NotEmpty
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getLegalId() {
		return legalId;
	}

	public void setLegalId(String legalId) {
		this.legalId = legalId;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}


}
