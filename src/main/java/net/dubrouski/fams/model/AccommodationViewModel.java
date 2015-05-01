package net.dubrouski.fams.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccommodationViewModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BigDecimal depositAmount;
	
	private String type;
	
	private String name;
	
	private boolean isActive;

	public Long getId() {
		return id;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString(){
		return "AVM: " + getId() + ", type: " + getType() + ", name: " + getName() + ", deposit:" + getDepositAmount();
	}
	
	
}
