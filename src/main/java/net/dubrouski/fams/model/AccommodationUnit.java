package net.dubrouski.fams.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.ForceDiscriminator;

import net.dubrouski.fams.util.AccommodationDeserializer;
/**
 * 
 * @author ondrej.prazak
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@ForceDiscriminator
@Table(name = "ACCOMMODATION_UNIT")
@JsonDeserialize(using = AccommodationDeserializer.class)
public abstract class AccommodationUnit implements Serializable, BaseEntity {

	private static final long serialVersionUID = 25L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "IS_ACTIVE",
			nullable = false,
			columnDefinition = "boolean default false")
	private boolean isActive;
	
	@Column(name = "DEPOSIT_AMOUNT",
			nullable = false,
			columnDefinition="Decimal(18,2) default '0.0'")
	private BigDecimal depositAmount;
	
	@Column(name = "NAME", columnDefinition="TEXT default ''")	
	private String name;
	
	@Column(name = "TYPE", insertable = false, updatable = false)
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
	@OneToOne(cascade = {CascadeType.REMOVE})
	private Price price;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "METER_DATA_ID")
	private MetersData metersData;
	
	private static final List<String> typesList = Arrays.asList("place", "room");
	
	public boolean isComposite(){
		if(this instanceof AccommodationComposite){
			return true; 
		}
		else{
			return false;
		}
	}
	
	public AccommodationComposite castToComposite(){
		return (AccommodationComposite) this;
	}	
	
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccommodationUnit ac = (AccommodationUnit) obj;
        if (this.getId() != ac.getId() && (this.getId() == null || !this.getId().equals(ac.getId()))) {
            return false;
        }
        return true;
    }
	
	@Override
    public int hashCode() {
        return 7 * this.getDepositAmount().hashCode()
        		+ 13 * this.getName().hashCode();
    }
	
	@Override
	public String toString(){
		return getClass().toString() + ": id: " + id + ": name: " + name + ": type: " + type; 
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public BigDecimal getDepositAmount() {
		return this.depositAmount;
	}

	public void setDepositAmount(BigDecimal deposit) {
		this.depositAmount = deposit;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public MetersData getMetersData() {
		return metersData;
	}

	public void setMetersData(MetersData metersData) {
		this.metersData = metersData;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static List<String> getTypesList(){
		return typesList;
	}
	
}
