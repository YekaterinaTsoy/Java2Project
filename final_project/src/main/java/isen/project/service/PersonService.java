package isen.project.service;




import java.util.List;

import db.daos.PersonDao;
import db.entities.Person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author 	Julia RUIZ CARRERO, Carlos JIMÉNEZ GARCÍA, Yekaterina TSOY
 *
 */
public class PersonService {
	private final ObservableList<Person> persons;

	private PersonService() {
		
		persons = FXCollections.observableArrayList();
		List<Person> listOfContacts = PersonDao.listPersons();
		persons.addAll(listOfContacts);

		
	}

	public static ObservableList<Person> getPersons() {

		return PersonServiceHolder.INSTANCE.persons;
	}


	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}



}
