package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import application.Main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class TestLog extends TestFXBase {
	
	  
	  @Override
	  public void start (Stage stage) throws Exception {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/Login.fxml"));
	        Pane root2 = (Pane) loader.load();
			Scene scene = new Scene(root2);
		    stage.setScene(scene);
		    stage.show();
		    stage.toFront();
	}
	
	  
	@Test
	public void correctLogin() throws InterruptedException{
		
		Button button = (Button) GuiTest.find("#load");
		TextField log = (TextField) GuiTest.find("#login");
		TextField pass = (TextField) GuiTest.find("#password");
		
		clickOn(log);
		write("log");
		
		clickOn(pass);
		write("pass");
		
		clickOn(button);
		
		assertEquals(log.getText(), "log");
		assertEquals(pass.getText(), "pass");
		
		Thread.sleep(1000);
	}
	
	@Test
	public void failLogin() throws InterruptedException{
		
		Button button = (Button) GuiTest.find("#load");
		Label lab = (Label) GuiTest.find("#isConnected");
		TextField log = (TextField) GuiTest.find("#login");
		TextField pass = (TextField) GuiTest.find("#password");
		
		clickOn(log);
		write("");
		Thread.sleep(1000);
		
		clickOn(pass);
		write("");
		Thread.sleep(1000);
		
		assertEquals(log.getText(), "");
		assertEquals(pass.getText(), "");
		
		clickOn(button);
		
		assertEquals(lab.getText(), "Login or Password isn't correct");
		
		Thread.sleep(1000);
	}
	
	@Test
	public void createAcc() throws InterruptedException{
		
		Button button = (Button) GuiTest.find("#createNewProfile");
		
		clickOn(button);
		
		Thread.sleep(1000);
	}
    
}