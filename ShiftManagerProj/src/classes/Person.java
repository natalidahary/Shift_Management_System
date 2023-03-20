package classes;

import interfaces.iSalary;

public abstract class Person implements iSalary{

	protected String firstName;
	protected String lastName;
	protected String iD;
	
	public Person(String firstName, String lastName, String iD) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.iD = iD;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}
	public void showEmployeeDetails() {
		// TODO Auto-generated method stub
		
	}
	
}
