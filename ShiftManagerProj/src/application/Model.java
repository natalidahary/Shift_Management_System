package application;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import classes.Employee;
import classes.Salary;
import classes.ShiftManager;
import classes.User;
import classes.WorkingDay;

@SuppressWarnings("serial")
public class Model implements Serializable {

	private static Model model;
	private User userManager; 
	private ShiftManager sManager;
	private Vector <String> startFinish; 
	private Vector <WorkingDay> temporaryScheduling;
	private String currentUser;
	private MyJDBC sql;
	
	private Model() {
		try {
			userManager = new User("java", "1234");
			sql = new MyJDBC();
			sManager = new ShiftManager(userManager);
			sManager.setAllEmployees(sql.readEmployees());
			sManager.setnumOfEmployees(sManager.getAllEmployees().size());
			startFinish = new Vector<String>();
			temporaryScheduling = new Vector<WorkingDay>();
			initializeWorkingDays();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Model getInstance() {
		if(model == null) {
			model = new Model();
		}
		return model;
	}
	public boolean checkifUserManager(String user, String pass) {
		if(!(userManager.getUserName().equals(user) || userManager.getUserName().equals(pass)))
			return false;
		
		else return true;
	}

	public boolean checkIfUserExist(String user, String pass) {
		if(!sManager.getAllEmployees().isEmpty())
		{
			for(int i=0; i<sManager.getEmployeeCount(); i++)
			{
				if(sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(user) && sManager.getAllEmployees().elementAt(i).getUser().getPassword().equals(pass)) {
					this.currentUser = user;
					return true;
				}
			}
		}
		return false;
	}
	public boolean checkUsernameAvailability(String username) {
		if(!sManager.getAllEmployees().isEmpty()) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(username))
					return false;
			}
		}
		return true;
	}
	
	public boolean addEmployee(String fName, String lName, String iD, String role, String hourlyWage, String user, String pass) {
		if(!sManager.getAllEmployees().isEmpty()){
			for(int i=0;i<sManager.getEmployeeCount(); i++) {
				if(sManager.getAllEmployees().elementAt(i).getiD().equals(iD) 
						|| sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(user))
					return false;
			}
		}		
		Double wage = Double.parseDouble(hourlyWage);
		User newUser = new User(user, pass);
		Employee emp = new Employee(fName, lName, iD, role, wage, newUser);
		sManager.setEmployee(emp);
		sql.addEmployee(emp);
		return true;
	}
	
	public boolean changePassword(String oldPass, String newPass) {
		User user = null;
		int spot = 0;
		if(!sManager.getAllEmployees().isEmpty()) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(currentUser)) {
					user = sManager.getAllEmployees().elementAt(i).getUser();
					spot = i;
				}
				
			}
			if(user.getPassword().equals(oldPass)) {
				if(user.getUserName().equals(currentUser)) {
					sManager.getUser().setPassword(newPass);
				}
				sql.changePassword(user, newPass);
				sManager.getAllEmployees().elementAt(spot).getUser().setPassword(newPass);
				return true;
			}
		}
		return false;
	}
	public Vector<String> getStartFinish() {
		return startFinish;
	}
	public void setStartFinish(Vector <String> startFinish) {
		this.startFinish = startFinish;
	}
	
	public User getUserManager() {
		return userManager;
	}


	public void setUserManager(User userManager) {
		this.userManager = userManager;
	}


	public ShiftManager getsManager() {
		return sManager;
	}


	public void setsManager(ShiftManager sManager) {
		this.sManager = sManager;
	}


	public MyJDBC getSql() {
		return sql;
	}


	public void setSql(MyJDBC sql) {
		this.sql = sql;
	}

	void printEmp() {
		System.out.println(sManager.getAllEmployees().elementAt(0));
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public Vector<WorkingDay> getTemporaryScheduling() {
		return temporaryScheduling;
	}
	public void setTemporaryScheduling(Vector<WorkingDay> temporaryScheduling) {
		this.temporaryScheduling = temporaryScheduling;
	}
	public void setWokringDay(String start, String finish, String  date, int position) {
		WorkingDay wd = new WorkingDay(date, start, finish);
		temporaryScheduling.insertElementAt(wd, position);
	}
	public void initializeWorkingDays() {
		for(int i=0; i<7; i++) {
			temporaryScheduling.insertElementAt(null, i);
		}
	}
	
	
	public boolean checkIdExists(String id) {
		if(!(sManager.getAllEmployees().isEmpty())) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getiD().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void sendShifts(String user) {
		if(!(sManager.getAllEmployees().isEmpty())) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(user)) {
					if(!temporaryScheduling.isEmpty()) {
						Salary salary = new Salary(temporaryScheduling);
						sManager.getAllEmployees().elementAt(i).addSalary(salary);
					//sql.removeEmployee(sManager.getAllEmployees().elementAt(i));
						}
					}
				}
			
		}
	}
	public void removeEmployee(String id) {
		if(!(sManager.getAllEmployees().isEmpty())) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getiD().equals(id)) {
					sql.removeEmployee(sManager.getAllEmployees().elementAt(i));
					sManager.removeEmployee(sManager.getAllEmployees().elementAt(i));
				}
			}
		}
	}
	
	public void generateSalaries() {
		if(!sManager.getAllEmployees().isEmpty()) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(!sManager.getAllEmployees().elementAt(i).getAllSalaries().isEmpty()) {
					int size = sManager.getAllEmployees().elementAt(i).getAllSalaries().size();
					double hourlyWage = sManager.getAllEmployees().elementAt(i).getHourlyWage();
					sManager.getAllEmployees().elementAt(i).getAllSalaries().elementAt(size-1).calculateSalary(hourlyWage);
				}
			}
		}
	}
	public Vector<String> showSalary(String user) {
		Vector <String> salaryData = new Vector<String>();
		if(!(sManager.getAllEmployees().isEmpty())) {
			for(int i=0; i<sManager.getEmployeeCount();i++) {
				if(sManager.getAllEmployees().elementAt(i).getUser().getUserName().equals(user)) {
					if(!sManager.getAllEmployees().elementAt(i).getAllSalaries().isEmpty()) {
						int size = sManager.getAllEmployees().elementAt(i).getAllSalaries().size();
						Salary s = sManager.getAllEmployees().elementAt(i).getAllSalaries().elementAt(size-1);
						salaryData.add(s.getWeek());
						Format df = new DecimalFormat ("0.00");
						salaryData.add(df.format(s.getBaseHours()));
						salaryData.add(df.format(s.getAdditionHours()));
						salaryData.add(df.format((s.getTotalSalary())));
						return salaryData;
					}
				}
			}
		}
		return null;
	}

}
