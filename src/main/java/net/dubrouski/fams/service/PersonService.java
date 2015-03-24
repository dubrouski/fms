package net.dubrouski.fams.service;

import java.util.Collection;

import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonService {
	public void savePerson(Person person);

	public Person getPersonById(Long id);
	
	public Collection<Person> listPersons();
}
