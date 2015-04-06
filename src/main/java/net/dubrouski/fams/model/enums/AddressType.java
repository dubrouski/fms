package net.dubrouski.fams.model.enums;

/**
 * @author stanislau.dubrouski
 *
 */
public enum AddressType {
	Registration("Registration"), Contact("Contact");

	private String label;

	private AddressType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
