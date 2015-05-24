package net.dubrouski.fams.test.helper;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.model.MetersData;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.model.enums.MeterType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

/**
 * Created by tmarton on 5/2/15.
 */
@Stateless
@Named(value = "contractTestHelper")
public class ContractTestHelper {

    @Inject
    PersonTestHelper helper;

    public Contract getContract() {
        Contract contract = new Contract();
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusDays(365));
        contract.setState(ContractState.New);


        MetersData data = new MetersData();
        data.setReadoutDate(LocalDate.now());
        MeterRecord meterRecord = new MeterRecord();
        meterRecord.setMeterType(MeterType.Electricity);

        data.addRecord(meterRecord);

        contract.setStartData(data);

        return contract;
    }
}
