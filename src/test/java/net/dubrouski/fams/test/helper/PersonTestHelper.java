package net.dubrouski.fams.test.helper;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.model.Person;

@Stateless
@Named(value = "personTestHepler")
public class PersonTestHelper {
	public Person getTestPerson() {
		Person p = new Person();
		p.setFirstName("Standa");
		p.setLastName("Novak");
		p.setBirthDate(LocalDate.of(1980, 12, 1));
		p.setEmail("email@email.com");
		p.setLegalId("KH1789789");
		p.setPhone("+420 777 777 777");
		p.setOtherNames("Bystry Voko");
		return p;
	}
}
