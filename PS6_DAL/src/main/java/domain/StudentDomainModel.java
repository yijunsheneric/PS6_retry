package domain;

import java.util.Date;
import java.util.UUID;

public class StudentDomainModel  {

	
	private UUID StudentID;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private Date DOB;
	
	
	protected StudentDomainModel() {
	}

	/**
	 * Student - This constructor will generate a new instance of Student.
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param dOB
	 */
	public StudentDomainModel(String firstName, String middleName, String lastName, Date dOB) {
		super();
		this.setStudentID(UUID.randomUUID());		
		FirstName = firstName;
		MiddleName = middleName;
		LastName = lastName;
		DOB = dOB;		
	}

	public StudentDomainModel(StudentDomainModel stu)
	{
		super();
		this.setStudentID(UUID.randomUUID());		
		FirstName = stu.getFirstName();
		MiddleName = stu.getMiddleName();
		LastName = stu.getLastName();
		DOB = stu.getDOB();
		
	}
	
	/**
	 * Student - This constructor will retrieve a give student ID's record.
	 * @param studentID
	 */
	public StudentDomainModel(UUID studentID) {
		super();
		StudentID = studentID;		
	}

	public UUID getStudentID() {
		return StudentID;
	}
	private void setStudentID(UUID studentID) {
		StudentID = studentID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	
}
