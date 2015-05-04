package net.dubrouski.fams.dao;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;

import java.util.List;

/**
 * Created by tmarton on 5/2/15.
 */
public interface ContractDao extends BaseDao<Contract, Long> {

    public List<Contract> getContractsByPerson(Person person);

    public List<Contract> getContractsByAccommodationUnit(AccommodationUnit accommodationUnit);

}
