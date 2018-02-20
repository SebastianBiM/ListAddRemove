package application.loginController;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML private TextField login;
	@FXML private PasswordField password;
	@FXML private Label isConnected;
	@FXML private Button load;
	@FXML private Button createNewProfile;
	
	private LoginModel loginModel = new LoginModel();
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			dataRunner();
			
			if(loginModel.isDbConnected()){
				isConnected.setText("Connected");
			} else {
				isConnected.setText("Not Connected");
			}
		}
		
	public void dataRunner(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				loginModel.logConnection();
			}
		});	
		
		t1.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
		
	@FXML	
	public void loadA(ActionEvent event) throws IOException, SQLException {
		try {
			if (loginModel.isLogin(login.getText(), password.getText())) {
				isConnected.setText("Login and Password are correct");

				((Node)event.getSource()).getScene().getWindow().hide();
	            Stage primaryStage5 = new Stage();
	            FXMLLoader loader = new FXMLLoader();
				Pane root5 = loader.load(getClass().getResource("/application/Main/Main.fxml").openStream());
				MainController mainController = (MainController)loader.getController();
				mainController.getUserName(loginModel.getUser());
				mainController.setTab(loginModel.getTab());
				mainController.listRunner();
				Scene scene5 = new Scene(root5);
				scene5.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage5.setScene(scene5);
				primaryStage5.setTitle("ListAddRemove");
				primaryStage5.show();
			} else {
				isConnected.setText("Login or Password isn't correct");
			}
		} catch (Exception e) {
				isConnected.setText("Login or Password isn't correct");
		}
		
	}
	
	 @FXML
	 public void createNewProfileA(ActionEvent event) throws Exception {
		 	Stage primaryStage9 = new Stage();
			Parent root9 = FXMLLoader.load(getClass().getResource("/application/addUserController/addUser.fxml"));
			Scene scene9 = new Scene(root9);
			scene9.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage9.setScene(scene9);
			primaryStage9.setTitle("Add User");
			primaryStage9.show();		
	 }
	 
	 @FXML
	 public void remindPassword(ActionEvent event) throws Exception {
		 	Stage primaryStage10 = new Stage();
			Parent root10 = FXMLLoader.load(getClass().getResource("/application/generatePassword/GenerateNewPassword.fxml"));
			Scene scene10 = new Scene(root10);
			scene10.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage10.setScene(scene10);
			primaryStage10.setTitle("Generate New Password");
			primaryStage10.show();		
	 }

	    
	public TextField getLogin() {
		return login;
	}

	public PasswordField getPassword() {
		return password;
	}

	public void setLogin(TextField login) {
		this.login = login;
	}

	public void setPassword(PasswordField password) {
		this.password = password;
	}
	 
}
