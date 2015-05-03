package net.dubrouski.fams.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.model.enums.ContractState;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CONTRACT")
public class Contract implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long Id;

	@NotNull(message = "{contract.validate.startDate.required}")
	@Column(name = "START_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate endDate;

	@NotNull(message = "{contract.validate.state.required}")
	@Enumerated(EnumType.STRING)
	@Column(name ="STATE")
	private ContractState state;
	
	@Column(name = "SIGN_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate signDate;
	
	@Column(name = "TERMINATION_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate terminationDate;
	
	@Column(name = "KEYS_HANDED_OVER")
	private boolean keysHandedOver = false;
	
	@Column(name = "KEYS_HANDOVER_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate keysHandoverDate;
	
	@Column(name = "TERMINATION_REQUEST_DATE")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate terminationRequestDate;

	@NotNull(message = "{contract.validate.tenant.required}")
	@OneToOne
	@JoinColumn(name = "TENANT_ID")
	private Person tenant;

	@NotNull(message = "{contract.validate.accommodation.required}")
	@ManyToOne
	@JoinColumn(name = "ACCOMMODATION_UNIT_ID")
	private AccommodationUnit accommodationUnit;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "START_DATA_ID")
	private MetersData startData;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "END_DATA_ID")
	private MetersData endData;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ContractState getState() {
		return state;
	}

	public void setState(ContractState state) {
		this.state = state;
	}

	public LocalDate getSignDate() {
		return signDate;
	}

	public void setSignDate(LocalDate signDate) {
		this.signDate = signDate;
	}

	public LocalDate getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

	public boolean isKeysHandedOver() {
		return keysHandedOver;
	}

	public void setKeysHandedOver(boolean keysHandedOver) {
		this.keysHandedOver = keysHandedOver;
	}

	public LocalDate getKeysHandoverDate() {
		return keysHandoverDate;
	}

	public void setKeysHandoverDate(LocalDate keysHandoverDate) {
		this.keysHandoverDate = keysHandoverDate;
	}

	public LocalDate getTerminationRequestDate() {
		return terminationRequestDate;
	}

	public void setTerminationRequestDate(LocalDate terminationRequestDate) {
		this.terminationRequestDate = terminationRequestDate;
	}

	public Person getTenant() {
		return tenant;
	}

	public void setTenant(Person tenant) {
		this.tenant = tenant;
	}

	public AccommodationUnit getAccommodationUnit() {
		return accommodationUnit;
	}

	public void setAccommodationUnit(AccommodationUnit accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}

	public MetersData getStartData() {
		return startData;
	}

	public void setStartData(MetersData startData) {
		this.startData = startData;
	}

	public MetersData getEndData() {
		return endData;
	}

	public void setEndData(MetersData endData) {
		this.endData = endData;
	}

}
