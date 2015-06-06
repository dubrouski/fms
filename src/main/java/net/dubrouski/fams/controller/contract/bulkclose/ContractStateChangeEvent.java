package net.dubrouski.fams.controller.contract.bulkclose;

import net.dubrouski.fams.model.Contract;

public class ContractStateChangeEvent {

	private Contract c;

	public ContractStateChangeEvent(Contract c) {
		this.c = c;
	}

	public Contract getContract() {
		return this.c;
	}
}
