package net.dubrouski.fams.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@Named(value = "personService")
public class PersonServiceImpl implements PersonService {

	@Inject
	PersonDao personDao;

	@Override
	public void savePerson(Person person) {
		personDao.save(person);

	}

	@Override
	public Person getPersonById(Long id) {
		return personDao.getByID(id);
	}

	@Override
	public List<Person> listPersons() {
		return new ArrayList<Person>(personDao.listAll());
	}
}
