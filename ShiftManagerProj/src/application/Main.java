package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;


public class Main extends Application{
	private static Stage stg;
	//private Model model = new Model();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			//primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Model changeScene(String fxml, Model model1)throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
		if(fxml.equals("TimePicker.fxml")) {
			stg.setWidth(383);
			stg.setHeight(264);
		}
		else if(fxml.equals("ChangePassword.fxml")) {
			stg.setWidth(443);
			stg.setHeight(233);
		}
		else if(fxml.equals("ShiftSchedulin.fxml")) {
			stg.setWidth(696);
			stg.setHeight(305);
		}
		else if(fxml.equals("RemoveEmployee.fxml")) {
			stg.setWidth(395);
			stg.setHeight(272);
		}
			else {
				stg.setWidth(483);
				stg.setHeight(364);
			}
		return model1;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
