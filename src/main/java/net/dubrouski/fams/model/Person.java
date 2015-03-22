package net.dubrouski.fams.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import net.dubrouski.fams.annotations.ValidateDateRanges;
import net.dubrouski.fams.converter.LocalDatePersistenceConverter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author stanislau.dubrouski
 * 
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull(message="{person.validate.fname.required}")
	@Length(min = 1, max = 255, message = "{person.validate.fname.length}")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotNull
	@Length(min = 1, max = 255, message = "sname cannot be empty")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Length(max = 255, message = "max len is 255")
	@Column(name = "OTHER_NAMES")
	private String otherNames;

	@NotNull
	@ValidateDateRanges
	@Column(name = "BIRTH_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
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

	@OneToMany(fetch = FetchType.LAZY)
	private List<PersonAddress> addresses = new ArrayList<PersonAddress>();

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

	public void addAddress(PersonAddress address) {
		this.addresses.add(address);
	}

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

}
