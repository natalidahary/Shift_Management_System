package classes;

import java.util.List;
import java.util.Vector;
import interfaces.iSalary;

public class ShiftManager implements iSalary{
	protected User user;
	protected int numOfEmployees;
	protected Vector <Employee> allEmployees;
	
	public ShiftManager(User user) {
		
		this.user = user;
		this.numOfEmployees = 0;
		this.allEmployees = new Vector <Employee>();
	}
	public int getEmployeeCount() {
		return numOfEmployees;
	}

	public Vector<Employee> getAllEmployees() {
		return allEmployees;
	}

	//Adds new employee
	public boolean setEmployee(Employee emp){
		allEmployees.addElement(emp);
		numOfEmployees++;
		System.out.println(emp);
		return true;
	}

	//Removes employee
	public void removeEmployee(Employee emp){

		if(!allEmployees.isEmpty())
		{
			if(allEmployees.contains(emp))
			{
				allEmployees.remove(emp);
				numOfEmployees--;
			}	
		}
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
	public void setAllEmployees(List<Employee> allEmp) {
		this.allEmployees = (Vector<Employee>) allEmp;
	}
	
	public void setnumOfEmployees(int num) {
		this.numOfEmployees = num;
	}

}
