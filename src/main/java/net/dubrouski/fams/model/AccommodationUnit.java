package net.dubrouski.fams.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForceDiscriminator;
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
	
	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
	@OneToOne(cascade = {CascadeType.REMOVE})
	private Price price;
	
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
		return getClass().toString() + ": id: " + id + ": name: " + name; 
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
	
}
