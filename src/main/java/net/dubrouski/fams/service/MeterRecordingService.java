package net.dubrouski.fams.service;

import java.util.Set;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;

/**
 * @author Stanislau.Dubrouski
 *
 */
public interface MeterRecordingService {
	public void addStartMetersRecordForContract(Contract contract,
			MeterRecord record);

	public void addFinishMetersRecordForContract(Contract contract,
			MeterRecord record);

	public Set<MeterRecord> getStartMeterRecordsForContract(Contract contract);

	public Set<MeterRecord> getEndMeterRecordsForContract(Contract contract);
}
