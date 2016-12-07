package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception {
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1997-7-17");
		} catch (ParseException e) {
			System.out.println("Error: Invalid Date Format.");
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Marist");
		person1.setMiddleName("V");
		person1.setLastName("Baron");
		person1.setBirthday(person1Birth);
		person1.setCity("Elysium");
		person1.setStreet("7 Hibana Drive");
		person1.setPostalCode(15379);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		// This will attempt to clean up any data written to the database
		
		ArrayList<PersonDomainModel> pers = PersonDAL.getPersons();
		PersonDomainModel per = null;
		
		// checking if it's empty
		
		if (pers.size() != 0) {
			
			for (PersonDomainModel p : pers) {

				// if it's not empty, get each person, delete them, and confirm at the end
				
				per = PersonDAL.getPerson(p.getPersonID());
				PersonDAL.deletePerson(p.getPersonID());
				
				assertNull("Cleaning up database...", per);
			}
		}
	}


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addPersonTest() {
		
		// logic: check if person is there; they shouldn't be
		// then, add person and check again; person should be there
		// if this is true, then the addPerson method works
		
		PersonDomainModel per;
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("Database is empty.", per);
		
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		
		System.out.println(person1.getPersonID() + " is in our records.");
		assertNotNull("Database is not empty.", per);
	
	}

	@Test
	public void getPersonTest() {
		
		// same logic as before; this test can confirm both the add and get methods
		
		PersonDomainModel per;
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("Database is empty.", per);
		
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		
		System.out.println(person1.getPersonID() + " is in our records.");
		assertNotNull("Database is not empty.", per);
		
	}
	
	@Test
	public void deletePersonTest() {
		
		// logic: check if person is there; they shouldn't be
		// then, add person and check again; person should be there
		// then, delete person and check again; person shouldn't be there
		// if this is true, then the deletePerson method works
		
		PersonDomainModel per;
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("Database is empty.", per);
		
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		
		System.out.println(person1.getPersonID() + " is in our records.");
		assertNotNull("Database is not empty.", per);

		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		
		assertNull("Database is empty.", per);

	}
	
	@Test
	public void updatePersonTest() {
		
		// logic: make a new name, add a person, and check the first name.
		// then change the first name, and then check it again to confirm.
		
		PersonDomainModel per;
		
		String oldFirstName = person1.getFirstName();
		String newFirstName = "Mark";
		
		per = PersonDAL.getPerson(person1.getPersonID());
		PersonDAL.addPerson(person1);

		assertTrue("Your first name is " + oldFirstName + ".", person1.getFirstName() == oldFirstName);
		
		person1.setFirstName(newFirstName);
		PersonDAL.updatePerson(person1);

		per = PersonDAL.getPerson(person1.getPersonID());

		assertTrue("Update: Your first name has changed from " + oldFirstName +" to " + newFirstName + ".", person1.getFirstName() == newFirstName);
	
	}
	
}