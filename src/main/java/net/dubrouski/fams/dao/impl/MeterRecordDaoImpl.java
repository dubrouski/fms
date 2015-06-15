package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.MeterRecordDao;
import net.dubrouski.fams.model.MeterRecord;

@Stateless
@Named(value = "meterRecordDao")
public class MeterRecordDaoImpl extends BaseDaoImpl<MeterRecord, Long> implements MeterRecordDao{

}
