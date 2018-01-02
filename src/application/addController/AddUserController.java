package application.addController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

import application.sqliteConnecction.SqliteConnection;


public class AddUserController implements Initializable {

	@FXML private TextField UserName;
	@FXML private TextField Login;
	@FXML private TextField uniqueWord;
	@FXML private PasswordField Password;
	@FXML private PasswordField confirmPassword;
	@FXML private Button close;
	@FXML private Button add;
	private Connection conn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				conn = SqliteConnection.Connector();
				if(conn == null){
					System.exit(1);
				}			
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
	
	 
	    public void insert(String User, String Login, String Password, String uniqueWord) {
	        String sql = "INSERT INTO User(User,Login,Password,UniqueWord) VALUES(?,?,?,?)";
	 
	        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, User);
	            pstmt.setString(2, DigestUtils.md5Hex(Login).toUpperCase());
	            pstmt.setString(3, DigestUtils.md5Hex(Password).toUpperCase());
	            pstmt.setString(4, DigestUtils.md5Hex(uniqueWord).toUpperCase());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	
	 @FXML
	 public void addButton(ActionEvent event) throws IOException {
		
		 if(UserName.getText().equals("") || Login.getText().equals("") || Password.getText().equals("") || confirmPassword.getText().equals("") || uniqueWord.getText().equals("")){
				Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Information");
			    alert.setHeaderText(null);
			    alert.setContentText("Please fill all of places");
			    alert.showAndWait(); 
		 } else {
				 if(Password.getText().equals(confirmPassword.getText())){
					 	insert(UserName.getText(), 
										Login.getText(), 
												Password.getText(),
													uniqueWord.getText());
						
					 	Stage stage = (Stage) close.getScene().getWindow();
					    stage.close();
					 } else {
						Alert alert = new Alert(AlertType.INFORMATION);
					    alert.setTitle("Error");
					    alert.setHeaderText(null);
					    alert.setContentText("Passwords aren't the same");
					    alert.showAndWait();
					 }
		 }

	}
	
	
	 @FXML
	 public void closeButton(ActionEvent event) throws IOException {
		    Stage stage = (Stage) close.getScene().getWindow();
		    stage.close();
	 }

}
