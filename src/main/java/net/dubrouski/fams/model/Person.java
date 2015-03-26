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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.dubrouski.fams.annotations.ValidateDateRanges;
import net.dubrouski.fams.converter.LocalDatePersistenceConverter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author stanislau.dubrouski
 * 
 */
@Entity
@Table(name = "PERSON")
public class Person implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotBlank(message = "{person.validate.fname.required}")
	@Length(min = 1, max = 255, message = "{person.validate.fname.length}")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotBlank(message = "{person.validate.sname.required}")
	@Length(min = 1, max = 255, message = "{person.validate.sname.length}")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Length(max = 255, message = "{person.validate.othernames.maxlength}")
	@Column(name = "OTHER_NAMES")
	private String otherNames;

	@NotNull(message = "{person.validate.birthdate.required}")
	@ValidateDateRanges(message = "person.validate.birthdate.ranges}")
	@Column(name = "BIRTH_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate birthDate;

	@NotBlank(message = "{person.validate.legalid.required}")
	@Length(min = 1, max = 255, message = "{person.validate.legalid.length}")
	@Column(name = "LEGAL_IDENTIFICATOR")
	private String legalId;

	@Column(name = "BUSINESS_ID", updatable = false)
	@GeneratedValue
	private Long businessId;

	@Column(name = "EMAIL")
	@NotBlank(message = "{person.validate.email.required}")
	@Email(message = "{person.validate.email.format}")
	private String email;

	@NotBlank(message = "{person.validate.phone.required}")
	@Length(min = 1, max = 255, message = "{person.validate.phone.format}")
	@Column(name = "PHONE")
	// @Phone TODO Create custom phone constraint
	private String phone;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PERSON2PERSON_ADDRESS", joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "PERSON_ADDRESS_ID"))
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
		return this.id + " " + this.getFirstName() + " " + this.getLastName();
	}


}
