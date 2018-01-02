package application.searchPerson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.textfield.*;

import application.Person.Person;

public class SearchPersonController implements Initializable {
	
	@FXML private TextField searchText;
	@FXML private Button searchButton;
	private ArrayList<String> listSearch = new ArrayList<String>();
	private ObservableList<Person> dataList;
	private TableView<Person> personTable;
	
	// autofill 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            
			String newWordT = StringUtils.capitalize(newValue);
            searchText.setText(newWordT);
			
			TextFields.bindAutoCompletion(searchText, listSearch);
		});
	}
	
	
	@FXML
	public void saveCloseA(ActionEvent event) throws IOException {
		
		for(Person x : dataList){
			if(x.getName().equals(searchText.getText()) || x.getSurname().equals(searchText.getText()) ){
				personTable.getSelectionModel().select(x);
			}
		}
		
		Stage stage = (Stage) searchButton.getScene().getWindow();
	    stage.close();
	}
	
	
	public void setDataList(ObservableList<Person> dataList) {
		this.dataList = dataList;
	}

	public void getDataList(ObservableList<Person> dataList) {
		for(Person s : dataList){
			listSearch.add(s.getName());
			listSearch.add(s.getSurname());
		}
	}
	
	public void getPersonTable(TableView<Person> personTable) {
	}

	public void setPersonTable(TableView<Person> personTable) {
		this.personTable = personTable;
	}
	
}
