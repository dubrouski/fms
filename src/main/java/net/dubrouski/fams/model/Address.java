package net.dubrouski.fams.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.dubrouski.fams.util.AddressSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

/**
 * @author stanislau.dubrouski
 *
 */
@Entity
@Table(name = "ADDRESS")
@JsonSerialize(using = AddressSerializer.class)
public class Address implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	private Country country;

	@Column(name = "CITY_NAME")
	@Length(min = 1, max = 255)
	private String city;

	@Column(name = "STREET_NAME")
	@Length(min = 1, max = 255)
	private String streetName;

	@Column(name = "STREET_NUM")
	@Length(min = 1, max = 255)
	private String streetNumber;

	@Column(name = "FLAT_NUM")
	@Length(max = 255)
	private String flatNumber;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
	List<AccommodationUnit> accommodations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public List<AccommodationUnit> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<AccommodationUnit> accommodations) {
		this.accommodations = accommodations;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address adr = (Address) obj;
        if (this.getId() != adr.getId() && (this.getId() == null || !this.getId().equals(adr.getId()))) {
            return false;
        }
        return true;
	}
	
	@Override
	public int hashCode() {
		return 3 * getCity().hashCode() + 5 * getStreetName().hashCode() +
				11 * getFlatNumber().hashCode();				
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city
				+ ", streetName=" + streetName + ", streetNumber="
				+ streetNumber + ", flatNumber=" + flatNumber + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
	public String toShortString(){
		return this.streetName + " " + this.streetNumber;
	}
	
	
}
