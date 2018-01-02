package application.generatePassword;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GeneratePasswordController implements Initializable {
	
	@FXML TextField uniqW;
	@FXML TextArea newPassArea;
	@FXML Button generatePass;
	@FXML Button finish;
	@FXML Button close;
	
	private GeneratePasswordModel generatePasswordModel = new GeneratePasswordModel();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		newPassArea.setEditable(false);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				generatePasswordModel.generatorPasswordModelConnection();
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
	
	Thread t2 = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				generatePasswordModel.setPass(uniqW.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
		
	
	 @FXML
	 public void generateNewPassword(ActionEvent event) throws IOException {
		 	try {
		 		generatePasswordModel.getPass(uniqW.getText());
		 		
		 		if(uniqW.getText().equals("")){
				    Alert alert = new Alert(AlertType.INFORMATION);
				    alert.setTitle("Information");
				    alert.setHeaderText(null);
				    alert.setContentText("Please Write Your Unique Word To Generate New Password");
				    alert.showAndWait();
		 		} else if (generatePasswordModel.getIfFind().equals("ResultSet closed")){
				    Alert alert = new Alert(AlertType.INFORMATION);
				    alert.setTitle("Information");
				    alert.setHeaderText(null);
				    alert.setContentText("Please Correct The Unique Word");
				    alert.showAndWait();
		 		} else {
		 			generatePasswordModel.randomPass();
		 			newPassArea.setText(String.valueOf(generatePasswordModel.getPass()));
		 			generatePass.setDisable(true);
		 		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 @FXML
	 public void finishButton(ActionEvent event) throws IOException, SQLException {
			
		 if(uniqW.getText().equals("")){
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Information");
			    alert.setHeaderText(null);
			    alert.setContentText("If You Don't Want To Generate New Password Press Close!");
			    alert.showAndWait();
		 } else {
			t2.start();
		    Stage stage = (Stage) finish.getScene().getWindow();
		    stage.close();
		 }
	 }
	 
	 @FXML
	 public void closeButton(ActionEvent event) throws IOException {
		    Stage stage = (Stage) close.getScene().getWindow();
		    stage.close();
	 }

}
