package net.dubrouski.fams.dao.impl;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.model.Contract;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by tmarton on 5/2/15.
 */
@Stateless
@Named(value = "contractDao")
public class ContractDaoImpl extends BaseDaoImpl<Contract, Long> implements ContractDao {
}
