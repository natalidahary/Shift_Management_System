package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Timer;


public class Controller implements Initializable {

	private Model model = Model.getInstance();
	Main m = new Main();
	String currentUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		model.getStartFinish().clear();
		stlbl_date1 = lbl_date1;
		stlbl_date2 = lbl_date2;
		stlbl_date3 = lbl_date3;
		stlbl_date4 = lbl_date4;
		stlbl_date5 = lbl_date5;
		stlbl_date6 = lbl_date6;
		stlbl_date7 = lbl_date7;
		stcombFinish = combFinish;
		stcombStart = combStart;
		stlbl_vi1 = lbl_vi1;
		stlbl_vi2 = lbl_vi2;
		stlbl_vi3 = lbl_vi3;
		stlbl_vi4 = lbl_vi4;
		stlbl_vi5 = lbl_vi5;
		stlbl_vi6 = lbl_vi6;
		stlbl_vi7 = lbl_vi7;
		stlbl_additionlH = lbl_additionlH;
		stlbl_regularH = lbl_regularH;
		stlbl_totalAmount = lbl_totalAmount;
		stlbl_week = lbl_week;

	}
	//LogIn
	@FXML
	private Button btn_logIn;
	@FXML
	private Label lbl_wrongLogIn;
	@FXML
	private TextField tf_username;
	@FXML
	private TextField tf_password;

	//AfterLogIn
	@FXML
	private Button btn_shiftSchedulin;
	@FXML
	private Button btn_showSalary;
	@FXML
	//private Button btn_changePassword;
	private Button btn_changePassword1;
	@FXML
	//private Button btn_logOut;
	private Button btn_logOut1;

	//AferLogInManager
	@FXML
	private Button btn_addEmployee;
	@FXML
	private Button btn_RemoveEmployee;
	@FXML
	private Button btn_generateSalaries;
	@FXML
	private Button btn_changePassword;
	@FXML
	private Button btn_logOut;

	//AddEmployee
	@FXML
	private Button btn_finish;
	@FXML
	private Button btn_cancelAddEmployee;
	@FXML
	private Label lbl_wrongDetail;
	@FXML
	private TextField tf_firstName;
	@FXML
	private TextField tf_lastName;
	@FXML
	private TextField tf_id;
	@FXML
	private TextField tf_role;
	@FXML
	private TextField tf_hourlyWage;
	@FXML
	private TextField tf_newUsername;

	//ChangePassword
	@FXML
	private Button btn_change;
	@FXML
	private Button btn_cancel;
	@FXML
	private Label lbl_wrongPassword;
	@FXML
	private TextField tf_oldPassword;
	@FXML
	private TextField tf_newPassword;

	//ShiftSchedualin
	@FXML
	private Button btn_send;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_day1;
	@FXML
	private Button btn_day2;
	@FXML
	private Button btn_day3;
	@FXML
	private Button btn_day4;
	@FXML
	private Button btn_day5;
	@FXML
	private Button btn_day6;
	@FXML
	private Button btn_day7;
	@FXML
	private Label lbl_vi1;
	@FXML
	private Label lbl_vi2;
	@FXML
	private Label lbl_vi3;
	@FXML
	private Label lbl_vi4;
	@FXML
	private Label lbl_vi5;
	@FXML
	private Label lbl_vi6;
	@FXML
	private Label lbl_vi7;


	private static Label stlbl_vi1;
	private static Label stlbl_vi2;
	private static Label stlbl_vi3;
	private static Label stlbl_vi4;
	private static Label stlbl_vi5;
	private static Label stlbl_vi6;
	private static Label stlbl_vi7;
	@FXML
	private Label lbl_date1;
	@FXML
	private Label lbl_date2;
	@FXML
	private Label lbl_date3;
	@FXML
	private Label lbl_date4;
	@FXML
	private Label lbl_date5;
	@FXML
	private Label lbl_date6;
	@FXML
	private Label lbl_date7;


	private static Label stlbl_date1;
	private static Label stlbl_date2;
	private static Label stlbl_date3;
	private static Label stlbl_date4;
	private static Label stlbl_date5;
	private static Label stlbl_date6;
	private static Label stlbl_date7;

	//ShowSalary
	@FXML
	private Button btn_exit;
	@FXML
	private Label lbl_additionlH;

	@FXML
	private Label lbl_regularH;

	@FXML
	private Label lbl_totalAmount;
	@FXML
	private Label lbl_week;
	
	private static Label stlbl_additionlH;
	private static Label stlbl_regularH;
	private static Label stlbl_totalAmount;
	private static Label stlbl_week;

	//RemoveEmployee
	@FXML
	private Button btn_cancelRemove;

	@FXML
	private Button btn_remove;

	@FXML
	private TextField tf_removeEmp;

	@FXML
	private Label lbl_idError;

	@FXML
	void action_userLogIn(ActionEvent event) {
		try {
			checkLogIn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void checkLogIn()throws Exception {

		if(model.checkifUserManager(tf_username.getText().toString(), tf_password.getText().toString())) {
			model.setCurrentUser(model.getUserManager().getUserName());
			lbl_wrongLogIn.setText("Signing you in.");
			m.changeScene("AfterLogInManager.fxml", model);
		}

		else if(model.checkIfUserExist(tf_username.getText().toString(), tf_password.getText().toString())) {
			this.currentUser = tf_username.getText().toString();
			lbl_wrongLogIn.setText("Signing you in.");
			m.changeScene("AfterLogIn.fxml", model);
		}
		else if(tf_username.getText().isEmpty() || tf_password.getText().isEmpty()) 
			lbl_wrongLogIn.setText("Please enter data !");

		else
			lbl_wrongLogIn.setText("Wrong username or password");
	}

	@FXML
	public void action_shiftSchedulin(ActionEvent event) throws Exception {
		m.changeScene("ShiftSchedulin.fxml", model);
		setWeekTexts();
	}
	@FXML
	public void action_showSalary(ActionEvent event) throws Exception {
		m.changeScene("ShowSalary.fxml", model);
		viewSalary();
	}
	@FXML
	public void action_changePassword1(ActionEvent event) throws Exception {
		m.changeScene("ChangePassword.fxml",model);
	}
	@FXML
	public void action_logOut1(ActionEvent event) throws Exception {
		m.changeScene("LogIn.fxml",model);
	}
	@FXML
	public void action_changePassword(ActionEvent event) throws Exception {
		m.changeScene("ChangePassword.fxml",model);
	}
	@FXML
	public void action_logOut(ActionEvent event) throws Exception {
		m.changeScene("LogIn.fxml",model);
	}

	//AfterLogInManager
	@FXML
	public void action_addEmployee(ActionEvent event) throws Exception {
		m.changeScene("AddEmployee.fxml",model);
	}

	@FXML
	void action_removeEmployee(ActionEvent event) throws IOException {
		m.changeScene("RemoveEmployee.fxml", model);
	}
	@FXML
	public void action_generateSalaries(ActionEvent event) throws Exception {
		model.generateSalaries();
		System.out.println("Salaries were generated successfully");
	}
	@FXML
	public void action_finish(ActionEvent event) throws Exception {
		checkEmployee();
	}
	@FXML
	void action_cancelAddEmployee(ActionEvent event) throws IOException {
		m.changeScene("AfterLogInManager.fxml", model);
	}

	public boolean checkEmployee()throws Exception {

		if(tf_firstName.getText().isEmpty() || tf_lastName.getText().isEmpty() 
				|| tf_id.getText().isEmpty() || tf_role.getText().isEmpty() ||
				tf_hourlyWage.getText().isEmpty() || tf_newUsername.getText().isEmpty() ||
				tf_newPassword.getText().isEmpty()) {
			lbl_wrongDetail.setText("Please enter data !");
			return false;
		}

		else if(!checkID(tf_id.getText()) || !checkIfDigitsOnly(tf_hourlyWage.getText())
				||!checkAlphabets(tf_firstName.getText()) || !checkAlphabets(tf_lastName.getText()) || !checkAlphabets(tf_role.getText())){
			lbl_wrongDetail.setText("Some or more fields are invalid.");
			return false;
		}

		else if(!model.checkUsernameAvailability(tf_newUsername.getText())) {
			lbl_wrongDetail.setText("Username is already taken.");
			return false;
		}

		else{
			model.addEmployee(tf_firstName.getText().toString(), tf_lastName.getText().toString(), tf_id.getText().toString(), tf_role.getText().toString(), tf_hourlyWage.getText().toString(), tf_newUsername.getText().toString(), tf_newPassword.getText().toString());
			m.changeScene("AfterLogInManager.fxml",model);
			return true;
		}
	}

	boolean checkID(String str) {
		if(str.length() == 9)
		{
			if(checkIfDigitsOnly(str))
				return true;
		}
		return false;
	}
	boolean checkIfDigitsOnly(String str) {
		if(str.matches("[0-9]+"))
			return true;
		return false;		
	}
	boolean checkAlphabets(String str) {
		if(str.matches("[a-zA-Z]+"))
			return true;
		return false;
	}

	@FXML
	public void action_change(ActionEvent event) throws Exception {
		checkPassword();
	}
	@FXML
	public void action_cancel(ActionEvent event) throws Exception {
		if(model.getCurrentUser().equals(model.getUserManager().getUserName()))
			m.changeScene("AfterLogInManager.fxml",model);
		else
			m.changeScene("AfterLogIn.fxml",model);
	}
	@FXML
	public void checkPassword()throws Exception {

		if(model.changePassword(tf_oldPassword.getText(), tf_newPassword.getText())) {
			System.out.println("Password has changed!");
			m.changeScene("AfterLogIn.fxml",model);
		}
		else if(tf_oldPassword.getText().isEmpty() || tf_newPassword.getText().isEmpty()) {
			lbl_wrongPassword.setText("Please enter data !");
		}

		else
			lbl_wrongPassword.setText("Wrong old password");
	}
	@FXML
	public void action_send(ActionEvent event) throws Exception {
		model.sendShifts(model.getCurrentUser());
		System.out.println("Shifts was updated!");
		m.changeScene("AfterLogIn.fxml",model);
	}
	@FXML
	public void action_back(ActionEvent event) throws Exception {
		System.out.println("Shifts schedulin canceled!");
		m.changeScene("AfterLogIn.fxml",model);
	}
	@FXML
	void action_day1(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(7).toString();
		actionDay(stlbl_vi1,currentDate, 0);
	}
	@FXML
	void action_day2(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(8).toString();
		actionDay(stlbl_vi2,currentDate, 1);
	}
	@FXML
	void action_day3(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(9).toString();
		actionDay(stlbl_vi3,currentDate, 2);
	}
	@FXML
	void action_day4(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(10).toString();
		actionDay(stlbl_vi4,currentDate, 3);
	}
	@FXML
	void action_day5(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(11).toString();
		actionDay(stlbl_vi5,currentDate, 4);
	}
	@FXML
	void action_day6(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(12).toString();
		actionDay(stlbl_vi6,currentDate, 5);
	}
	@FXML
	void action_day7(ActionEvent event) throws IOException {
		model.getStartFinish().clear();
		String currentDate = LocalDate.now().plusDays(13).toString();
		actionDay(stlbl_vi7,currentDate, 6);
	}

	//ShowSalary
	public void showSalary() {
		// TODO Auto-generated method stub

	}

	public void setUserInformation(String username) {
		//lbl_welcome.setText("Welcome " + username + "!");
	}

	public Vector<String> readHours() throws IOException {
		Vector <String> times = new Vector<String>();
		BufferedReader reader = new BufferedReader(new FileReader("Hours.txt"));
		String line;
		while((line = reader.readLine()) !=null) {
			times.add(line);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("Hours.txt"));
		new FileWriter("Hours.txt").close();
		return times;
	}
	public void setWeekTexts() {
		LocalDate currentDate = LocalDate.now();
		String current = currentDate.toString();
		current = currentDate.plusDays(7).toString();
		stlbl_date1.setText(current);
		current = currentDate.plusDays(8).toString();
		stlbl_date2.setText(current);
		current = currentDate.plusDays(9).toString();
		stlbl_date3.setText(current);
		current = currentDate.plusDays(10).toString();
		stlbl_date4.setText(current);
		current = currentDate.plusDays(11).toString();
		stlbl_date5.setText(current);
		current = currentDate.plusDays(12).toString();
		stlbl_date6.setText(current);
		current = currentDate.plusDays(13).toString();
		stlbl_date7.setText(current);
	}


	///////////////////////////////////////////////////////////////
	//TimePicker
	@FXML
	private Button btn_cancelTime;

	@FXML
	private Button btn_doneTime;

	@FXML
	private ComboBox<String> combStart;

	@FXML
	private ComboBox<String> combFinish;

	private static ComboBox<String> stcombStart;

	private static ComboBox<String> stcombFinish;

	@FXML
	private Label lbl_errorHours;

	@FXML
	void action_cancelTime(ActionEvent event) throws IOException {
		m.changeScene("ShiftSchedulin.fxml",model);
		setWeekTexts();
	}

	@FXML
	void action_doneTime(ActionEvent event) throws IOException {
		Vector <String> times = new Vector<String>();
		times.add(combStart.getValue());
		times.add(combFinish.getValue());
		if(checkHours(times)) {
			writeHours(times);
			//model.setStartFinish(times);
			m.changeScene("ShiftSchedulin.fxml",model);
			setWeekTexts();
		}
		else 
			lbl_errorHours.setText("Invalid hours!");
	}

	public void startBoxes() {
		ObservableList <String> arr = FXCollections.observableArrayList(setTimeBoxes());
		stcombFinish.setItems(arr);
		stcombStart.setItems(arr);
	}
	public ObservableList<String> setTimeBoxes() {
		ObservableList <String> arr = FXCollections.observableArrayList();
		for(int i=0; i<24;i++) {
			for(int j=0; j<60; j++) {
				if(i<10) {
					if(j<10) 
						arr.add("0"+i+":"+"0"+j);

					else 
						arr.add("0"+i+":"+j);
				}
				else {
					if(j<10) {
						arr.add(i+":"+"0"+j);
					}
					else 
						arr.add(i+":"+j);
				}
			}
		}
		return arr;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	public boolean checkHours(Vector<String> hours) {
		String start,finish;
		if(!hours.isEmpty()) {
			start = hours.elementAt(0);
			finish = hours.elementAt(1);
			if(timePosition(finish)<=timePosition(start))
				return false;
		}
		return true;
	}

	public int timePosition(String str) {
		Vector<String> times = new Vector<String>();
		times.addAll(setTimeBoxes());
		int position = times.indexOf(str);
		return position;
	}

	public void writeHours(Vector <String> hours) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Hours.txt"));
		writer.write(hours.elementAt(0) +"\n");
		writer.write(hours.elementAt(1));
		writer.close();
		
		
		//////////////////////////////////////////
		
	}
	public void actionDay(Label vi,String date, int position) throws IOException {
		m.changeScene("TimePicker.fxml", model);
		startBoxes();
		model.setStartFinish(readHours());
		if(!(model.getStartFinish().isEmpty()))
		{
			model.setWokringDay(model.getStartFinish().elementAt(0), model.getStartFinish().elementAt(1), date, position);
			vi.setText("✔");
			vi.setVisible(true);
		}
		else
			vi.setText("");
		model.getStartFinish().clear();
	}

	@FXML
	void action_exit(ActionEvent event) throws IOException {
		m.changeScene("AfterLogIn.fxml", model);
	}

	@FXML
	void action_cancelRemove(ActionEvent event) throws IOException {
		m.changeScene("AfterLogInManager.fxml", model);
	}

	@FXML
	void action_remove(ActionEvent event) throws IOException {
		if(tf_removeEmp.getText().isEmpty())
			lbl_idError.setText("Please insert ID");
		else if(!model.checkIdExists(tf_removeEmp.getText())) {
			lbl_idError.setText("ID not found");
		}
		else {
			model.removeEmployee(tf_removeEmp.getText());
			System.out.println("Employee " + tf_removeEmp.getText() + " was removed successfully!" );
			m.changeScene("AfterLogInManager.fxml", model);
		}
	}
	
	void viewSalary() {
		if(!(model.showSalary(model.getCurrentUser()) == null)){
			Vector <String> salaryData = model.showSalary(model.getCurrentUser());
			stlbl_week.setText(salaryData.elementAt(0));
			stlbl_regularH.setText(salaryData.elementAt(1));
			stlbl_additionlH.setText(salaryData.elementAt(2));
			stlbl_totalAmount.setText("₪ " + salaryData.elementAt(3));
		}
	}

}
