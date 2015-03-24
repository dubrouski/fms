package net.dubrouski.fams.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
public abstract class AccommodationUnit implements Serializable {
	
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
	
	@Column(name = "NAME")
	private String name;	
	
	
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccommodationComposite ac = (AccommodationComposite) obj;
        if (this.getId() != ac.getId() && (this.getId() == null || !this.getId().equals(ac.getId()))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 7 * this.getDepositAmount().hashCode()
        		+ 13 * this.getName().hashCode();
    }
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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

}
