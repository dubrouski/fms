package net.dubrouski.fams.service;

import java.util.List;

import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
public interface PersonService {
	
	public void savePerson(Person person);

	public Person getPersonById(Long id);
	
	public Person getPersonByLegalId(String id);
	
	public List<Person> listPersons();
	
	public void delete(Person person);
}
