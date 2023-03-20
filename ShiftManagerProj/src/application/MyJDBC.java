package application;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import classes.Employee;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MyJDBC {
	private static Connection connection; 

	public MyJDBC() throws SQLException{	
		String url= "jdbc:mysql://localhost:3306/shiftmanager";
		String user = "natalid";
		String pass = "shiiiran55";
		connection = DriverManager.getConnection(url, user, pass);

	}

	public List <Employee> readEmployees() {
		List <Employee> allEmployees = new Vector <Employee>();
		PreparedStatement psUsers = null;
		ResultSet resultSetUsers = null;
		PreparedStatement psEmployee = null;
		ResultSet resultSetEmployee = null;
		PreparedStatement psPerson = null;
		ResultSet resultSetPerson = null;

		try {
			psUsers = connection.prepareStatement("SELECT * FROM user_table");
			resultSetUsers = psUsers.executeQuery();
			psEmployee = connection.prepareStatement("SELECT * FROM employee_table");;
			resultSetEmployee = psEmployee.executeQuery();
			psPerson = connection.prepareStatement("SELECT * FROM person_table");;
			resultSetPerson = psPerson.executeQuery();
			
			while(resultSetUsers.next() && resultSetEmployee.next() && resultSetPerson.next()) {
				Employee emp = new Employee(null, null, null, null, 0, null);
				User u = new User(null,null);
				u.setPassword(resultSetUsers.getString("password"));
				u.setUserName(resultSetUsers.getString("username"));
				emp.setUser(u);
				emp.setFirstName(resultSetPerson.getString("first_name"));
				emp.setLastName(resultSetPerson.getString("last_name"));
				emp.setiD(resultSetPerson.getString("id"));
				emp.setRole(resultSetEmployee.getString("role"));
				emp.setHourlyWage(Double.parseDouble(resultSetEmployee.getString("hourly_wage")));
				allEmployees.add(emp);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return allEmployees;
	}

	public static void changeScene(ActionEvent event, String fxmlFile, String username) {
		Parent root = null;
		if(username != null) {
			try {
				FXMLLoader loader = new FXMLLoader(MyJDBC.class.getResource(fxmlFile));
				root = loader.load();
				Controller controller = loader.getController();
				controller.setUserInformation(username);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try{
				root = FXMLLoader.load(MyJDBC.class.getResource(fxmlFile));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		Stage stage = (Stage) ((Stage)((event.getSource()))).getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void addEmployee(Employee emp) {
		PreparedStatement psInsert = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiftmanager", "natalid", "shiiiran55");
				psInsert = connection.prepareStatement("INSERT INTO person_table (id, first_name ,last_name) values (?,?,?)");
				psInsert.setString(1, emp.getiD());
				psInsert.setString(2, emp.getFirstName());
				psInsert.setString(3, emp.getLastName());
				psInsert.executeUpdate();
				psInsert.close();
				psInsert = connection.prepareStatement("INSERT INTO employee_table(employee_id, serial_number ,role, hourly_wage) values (?,?,?,?)");
				psInsert.setString(1, emp.getiD());
				psInsert.setString(2, "0");
				psInsert.setString(3, emp.getRole());
				psInsert.setString(4, String.valueOf(emp.getHourlyWage()));
				psInsert.executeUpdate();
				psInsert.close();
				psInsert = connection.prepareStatement("INSERT INTO user_table(user_id, username, password) values (?,?,?)");
				psInsert.setString(1, emp.getiD());
				psInsert.setString(2, (emp.getUser().getUserName()));
				psInsert.setString(3, (emp.getUser().getPassword()));
				psInsert.executeUpdate();
				psInsert.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeEmployee(Employee emp) {
PreparedStatement psInsert = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiftmanager", "natalid", "shiiiran55");
				psInsert = connection.prepareStatement("DELETE FROM person_table WHERE id = ? ;");
				psInsert.setString(1, emp.getiD());
				psInsert.executeUpdate();
				psInsert.close();
				psInsert = connection.prepareStatement("DELETE FROM employee_table WHERE employee_id = ? ;");
				psInsert.setString(1, emp.getiD());
				psInsert.executeUpdate();
				psInsert.close();
				psInsert = connection.prepareStatement("DELETE FROM user_table WHERE user_id = ? ;");
				psInsert.setString(1, emp.getiD());
				psInsert.executeUpdate();
				psInsert.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changePassword(User user, String newPass) {
		PreparedStatement psInsert = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiftmanager", "natalid", "shiiiran55");
				psInsert = connection.prepareStatement("UPDATE user_table SET password = ? WHERE username = ?;");
				psInsert.setString(1, (newPass));
				psInsert.setString(2, (user.getUserName()));
				psInsert.executeUpdate();
				psInsert.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void logInUser(ActionEvent event,String username,String password) {
		connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shiftmanager", "natalid", "shiiiran55");
			preparedStatement = connection.prepareStatement("SELECT password from user_table WHERE username = ?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if(!resultSet.isBeforeFirst()) {
				System.out.println("User not found!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Provided credentials are incorrect!");
				alert.show();

			}
			else {
				while(resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					System.out.println(retrievedPassword);
					if(retrievedPassword.equals(password)) {
						if(username.equals("java")) {
							changeScene(event, "AfterLogInManager.fxml", username);
						}
						else
							changeScene(event, "AfterLogIn.fxml", username);		
					}
					else {
						System.out.println("Password did not match!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Provided credentials are incorrect!");
						alert.show();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(resultSet!=null) {
				try {
					resultSet.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}
				if(preparedStatement!=null) {
					try {
						preparedStatement.close();
					}catch(SQLException E) {
						E.printStackTrace();
					}
					if(connection!=null) {
						try {
							connection.close();
						}catch(SQLException E) {
							E.printStackTrace();
						}
					}
				}
			}
		}
	}
}
