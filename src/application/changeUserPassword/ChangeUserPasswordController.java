package application.changeUserPassword;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import application.sqliteConnecction.SqliteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChangeUserPasswordController implements Initializable {

	@FXML private PasswordField newPass;
	@FXML private PasswordField confirmNewPass;
	@FXML private Button close;
	@FXML private Button save;
	
	private Connection conn;
	private String[] tab;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Thread t2 = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				changePassword();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	public void changePassword() throws SQLException {
		
		PreparedStatement preparedStatement = null;
		String query3 = "update User set User ='"+tab[0]+"',"
				+ "Login ='"+tab[1]+"',"
						+ "Password ='"+DigestUtils.md5Hex(String.valueOf(newPass.getText())).toUpperCase()+"',"
								+ "UniqueWord = '"+tab[3]+"' "
										+ "where User ='"+tab[0]+"'";
		
		try {
			preparedStatement = conn.prepareStatement(query3);
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			preparedStatement.close();
		}
	}
	
	 @FXML
	 public void saveButton(ActionEvent event) throws IOException, SQLException {
		 
		 if(newPass.getText().equals("")){
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Information");
			    alert.setHeaderText(null);
			    alert.setContentText("If You Don't Want To Change Password Press Close!");
			    alert.showAndWait();
		 } else {
				 if(newPass.getText().equals(confirmNewPass.getText())){
					 t2.start();
					 Stage stage = (Stage) save.getScene().getWindow();
					 stage.close();
				 } else {
					    Alert alert = new Alert(AlertType.INFORMATION);
					    alert.setTitle("Information");
					    alert.setHeaderText(null);
					    alert.setContentText("Passwords Have To Be The Same!");
					    alert.showAndWait();
				 }
		 }
	 }
	
	 @FXML
	 public void closeButton(ActionEvent event) throws IOException {
		    Stage stage = (Stage) close.getScene().getWindow();
		    stage.close();
	 }

	public String[] getTab() {
		return tab;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}

}
