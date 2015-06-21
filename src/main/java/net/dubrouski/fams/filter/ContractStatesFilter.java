package net.dubrouski.fams.filter;

import net.dubrouski.fams.model.enums.ContractState;

public class ContractStatesFilter implements SearchFilter {

	private ContractState[] states;

	public ContractStatesFilter(ContractState[] states) {
		this.states = states;
	}

	public ContractState[] getStates() {
		return states;
	}
}
