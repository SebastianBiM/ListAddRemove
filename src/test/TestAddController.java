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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestAddController extends TestFXBase {
	

	  @Override
	  public void start (Stage stage) throws Exception {
//	    Parent mainNode = FXMLLoader.load(Main.class.getResource("/application/Main.fxml"));
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/Main/Main.fxml"));
        Pane root2 = (Pane) loader.load();
		Scene scene = new Scene(root2);
	    stage.setScene(scene);
	    stage.show();
	    stage.toFront();
	}
	  
	  @Before
	  public void findMenu(){
			clickOn("#allMenu");
			clickOn("#fileMenu");
			clickOn("#addF");
	  }

 
	@Test
	public void addPerson() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("Sebastian");
		
		clickOn(surname);
		write("Miko³ajewicz");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("Milicz");
		
		clickOn(rio1);
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	

		clickOn(finish);
		
		assertEquals(name.getText(), "Sebastian");
		assertEquals(surname.getText(), "Miko³ajewicz");
		assertEquals(loc.getText(), "Milicz");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "");
		assertEquals(lab2.getText(), "");
		assertEquals(lab3.getText(), "");
		assertEquals(lab4.getText(), "");

		Thread.sleep(1000);
	}
	
	@Test
	public void addFailPerson1() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("23");
		
		clickOn(surname);
		write("Miko³ajewicz");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("Milicz");
		
		clickOn(rio1);
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	
		
		clickOn(finish);
		
		assertEquals(name.getText(), "23");
		assertEquals(surname.getText(), "Miko³ajewicz");
		assertEquals(loc.getText(), "Milicz");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "Only letters");
		assertEquals(lab2.getText(), "");
		assertEquals(lab3.getText(), "");
		assertEquals(lab4.getText(), "Something is not correct");
		
		Thread.sleep(1000);
	}
	
	@Test
	public void addFailPerson2() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("Sebastian");
		
		clickOn(surname);
		write("32");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("Milicz");
		
		clickOn(rio1);
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	
		
		clickOn(finish);
		
		assertEquals(name.getText(), "Sebastian");
		assertEquals(surname.getText(), "32");
		assertEquals(loc.getText(), "Milicz");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "");
		assertEquals(lab2.getText(), "Only letters");
		assertEquals(lab3.getText(), "");
		assertEquals(lab4.getText(), "Something is not correct");

		Thread.sleep(1000);
	}
	
	@Test
	public void addFailPerson3() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("");
		
		clickOn(surname);
		write("Miko³ajewicz");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("Milicz");
		
		clickOn(rio1);
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	
		
		clickOn(finish);
		
		assertEquals(name.getText(), "");
		assertEquals(surname.getText(), "Miko³ajewicz");
		assertEquals(loc.getText(), "Milicz");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "");
		assertEquals(lab2.getText(), "");
		assertEquals(lab3.getText(), "");
		assertEquals(lab4.getText(), "All of places have to be filled");

		Thread.sleep(1000);
	}
	
	@Test
	public void addFailPerson4() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("Sebastian");
		
		clickOn(surname);
		write("Miko³ajewicz");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("123");
		
		clickOn(rio1);
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	
		
		clickOn(finish);
		
		assertEquals(name.getText(), "Sebastian");
		assertEquals(surname.getText(), "Miko³ajewicz");
		assertEquals(loc.getText(), "123");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "");
		assertEquals(lab2.getText(), "");
		assertEquals(lab3.getText(), "Only letters");
		assertEquals(lab4.getText(), "Something is not correct");
		
		Thread.sleep(1000);
	}

	
	@Test
	public void addFailPerson5() throws InterruptedException {

		TextField name = (TextField) GuiTest.find("#nameT");
		TextField surname = (TextField) GuiTest.find("#surnameT");
		TextField loc = (TextField) GuiTest.find("#locT");
		DatePicker date = (DatePicker) GuiTest.find("#dateOfBirth");
		RadioButton rio1 = (RadioButton) GuiTest.find("#sexM");
		RadioButton rio2 = (RadioButton) GuiTest.find("#sexF");
		ChoiceBox<String> users = (ChoiceBox<String>) GuiTest.find("#addToUser");
		Label lab1 = (Label) GuiTest.find("#lblN");
		Label lab2 = (Label) GuiTest.find("#lblS");
		Label lab3 = (Label) GuiTest.find("#lblL");
		Label lab4 = (Label) GuiTest.find("#lblCheck");
		Button finish = (Button) GuiTest.find("#finish");
		
		clickOn(name);
		write("Sebastian");
		
		clickOn(surname);
		write("Miko³ajewicz");
		
		clickOn(date);
		write("04.06.1997");
		
		clickOn(loc);
		write("Milicz");
		
		clickOn(users);
		Thread.sleep(1000);
		clickOn(users.getItems().get(0));	
		
		clickOn(finish);
		
		assertEquals(name.getText(), "Sebastian");
		assertEquals(surname.getText(), "Miko³ajewicz");
		assertEquals(loc.getText(), "Milicz");
		assertEquals(date.getEditor().getText(), "04.06.1997");
		assertEquals(lab1.getText(), "");
		assertEquals(lab2.getText(), "");
		assertEquals(lab3.getText(), "");
		assertEquals(lab4.getText(), "All of places have to be filled");
		
		Thread.sleep(1000);
	}
}
