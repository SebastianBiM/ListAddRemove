package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestCreateUser extends TestFXBase {

	  @Override
	  public void start (Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/loginController/Login.fxml"));
        Pane root2 = (Pane) loader.load();
		Scene scene = new Scene(root2);
	    stage.setScene(scene);
	    stage.show();
	    stage.toFront();
	}
	  
	  @Before
	  public void findCreateButton(){
		clickOn("#createNewProfile");  
	  }


	@Test
	public void createUser() throws InterruptedException {

		TextField UserName = (TextField) GuiTest.find("#UserName");
		TextField Login = (TextField) GuiTest.find("#Login");
		TextField UniqueWord = (TextField) GuiTest.find("#uniqueWord");
		PasswordField Password = (PasswordField) GuiTest.find("#Password");
		PasswordField confirmPassword = (PasswordField) GuiTest.find("#confirmPassword");
		Button close = (Button) GuiTest.find("#close");
		Button add = (Button) GuiTest.find("#add");
		
		clickOn(UserName);
		write("Test");
		
		clickOn(Login);
		write("123");
		
		clickOn(Password);
		write("1234");
		
		clickOn(confirmPassword);
		write("1234");
		
		clickOn(UniqueWord);
		write("test");

		clickOn(add);
		
		assertEquals(UserName.getText(), "Test");
		assertEquals(Login.getText(), "123");
		assertEquals(Password.getText(), "1234");
		assertEquals(UniqueWord.getText(), "test");
		assertEquals(Password.getText(), confirmPassword.getText());
		
		clickOn("#login");
		write("123");
		
		clickOn("#password");
		write("1234");
		
		clickOn("#load");
		
		Thread.sleep(1000);
	}

}
