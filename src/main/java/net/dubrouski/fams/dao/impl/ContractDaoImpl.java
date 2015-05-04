package net.dubrouski.fams.dao.impl;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by tmarton on 5/2/15.
 */
@Stateless
@Named(value = "contractDao")
public class ContractDaoImpl extends BaseDaoImpl<net.dubrouski.fams.model.Contract, Long> implements ContractDao {

    public List<Contract> getContractsByPerson(Person person) {
        if (person == null) {
            throw new FmsException("Person cannot be null.");
        }

        TypedQuery<Contract> query = this.entityManager.createQuery("select c from Contract c join c.tenant tenant where tenant.id = :id", Contract.class);
        return query.setParameter("id",person.getId()).getResultList();
    }

    public List<Contract> getContractsByAccommodationUnit(AccommodationUnit accommodationUnit) {
        if (accommodationUnit == null) {
            throw new FmsException("Address cannot be null.");
        }

        TypedQuery<Contract> query = this.entityManager.createQuery("select c from Contract c join c.accommodationUnit au where au.id = :id", Contract.class);
        return query.setParameter("id",accommodationUnit.getId()).getResultList();
    }
}
