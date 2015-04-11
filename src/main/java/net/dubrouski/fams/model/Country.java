package net.dubrouski.fams.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author stanislau.dubrouski
 * Entity defines the list of countries.
 */
@Entity
@Table(name = "COUNTRY", uniqueConstraints = @UniqueConstraint(columnNames = "ISO_CODE"))
public class Country implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ISO_CODE")
	@NotEmpty
	private String code;
	
	@Column(name="READABLE_NAME")
	private String readableName;
	
	@Column(name="ORDERING")
	private int order;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country adr = (Country) obj;
        if (this.getId() != adr.getId() && (this.getId() == null || !this.getId().equals(adr.getId()))) {
            return false;
        }
        return true;
	}
	
	@Override
	public int hashCode() {
		return 3 * getCode().hashCode();				
	}
	
	
}
