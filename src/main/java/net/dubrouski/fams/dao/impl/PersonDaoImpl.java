package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateless
@Named(value = "personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person, Long> implements
		PersonDao {

}
