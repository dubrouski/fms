package net.dubrouski.fams.service.impl;

import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.dao.MeterRecordDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.service.ContractService;
import net.dubrouski.fams.service.MeterRecordingService;

/**
 * @author Stanislau.Dubrouski
 *
 */
public class MeterRecordingServiceImpl implements MeterRecordingService {

	@Inject
	ContractDao contractDao;

	@Inject
	MeterRecordDao meterRecordDao;

	@Inject
	ContractService contractService;

	@Inject
	Logger logger;

	@Override
	public void addStartMetersRecordForContract(Contract contract,
			MeterRecord record) {
		logger.info("Start meter record " + record.toString()
				+ " received for contract " + contract.getCode());
		meterRecordDao.save(record);
		Contract contractWithData = contractDao
				.getContractWithMetersData(contract.getId());
		contractWithData.addStartMeterRecord(record);
		contractService.updateContract(contractWithData);
	}

	@Override
	public void addFinishMetersRecordForContract(Contract contract,
			MeterRecord record) {
		logger.info("Finishing meter record " + record.toString()
				+ "received for contract " + contract.getCode());		
		meterRecordDao.save(record);
		Contract contractWithData = contractDao
				.getContractWithMetersData(contract.getId());
		contractWithData.addFinishMeterRecord(record);
		contractService.updateContract(contractWithData);
	}

	@Override
	public Set<MeterRecord> getStartMeterRecordsForContract(Contract contract) {
		return contractDao.getContractWithMetersData(contract.getId())
				.getStartData().getRecords();
	}

	@Override
	public Set<MeterRecord> getEndMeterRecordsForContract(Contract contract) {
		return contractDao.getContractWithMetersData(contract.getId())
				.getEndData().getRecords();
	}

}
