package classes;


import java.util.Vector;

public class Employee extends Person{

	protected String role;
	private static int SerialGenerator = 0;
	private int serialNumber;
	protected double hourlyWage;
	protected Vector <Salary> allSalaries;
	protected User user;
	
	public Employee(String firstName, String lastName, String iD, String role, double hourlyWage, User user) {
		super(firstName, lastName, iD);
		this.role = role;
		this.serialNumber = SerialGenerator++;
		this.hourlyWage = hourlyWage;
		this.allSalaries = new Vector <Salary>();
		this.user = user;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public double getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public Vector<Salary> getAllSalaries() {
		return allSalaries;
	}

	@Override
	public void showSalary() {
		// TODO Auto-generated method stub
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public void addSalary(Salary salary) {
		this.allSalaries.add(salary);
	}
	@Override
	public String toString() {
		return "Employee [name=" + firstName + "last name=" + lastName +"iD="+ iD + role + ", serialNumber=" + serialNumber + ", hourlyWage=" + hourlyWage
				+ ", allSalaries=" + allSalaries + ", user=" + user + "]";
	}
	
}
