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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import net.dubrouski.fams.annotations.PhoneNumber;
import net.dubrouski.fams.annotations.ValidateDateRanges;
import net.dubrouski.fams.converter.LocalDatePersistenceConverter;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author stanislau.dubrouski
 * 
 */
@Entity
@Table(name = "PERSON", uniqueConstraints = { @UniqueConstraint(columnNames = "LEGAL_IDENTIFICATOR") })
public class Person implements Serializable {

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

	@Column(name = "CODE", columnDefinition = "serial")
	@Generated(GenerationTime.INSERT)
	private Long code;

	@Column(name = "EMAIL")
	@NotBlank(message = "{person.validate.email.required}")
	@Email(message = "{person.validate.email.format}")
	private String email;

	@NotBlank(message = "{person.validate.phone.required}")
	@Length(min = 1, max = 255, message = "{person.validate.phone.format}")
	@Column(name = "PHONE")
	@PhoneNumber(message = "{person.validate.phone.formatting}")
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

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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

	public List<PersonAddress> getAddresses() {
		return addresses;
	}

	public void addAddress(PersonAddress address) {
		this.addresses.add(address);
	}

	@Override
	public String toString() {
		return this.id + " " + this.getFirstName() + " " + this.getLastName();
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
		Person other = (Person) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
}
