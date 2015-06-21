package net.dubrouski.fams.filter;

public class ContractCodeFilter implements SearchFilter {

	private Long code;

	public ContractCodeFilter(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
}
