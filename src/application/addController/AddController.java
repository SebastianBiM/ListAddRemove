package application.addController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;

import application.Person.Person;
import application.sqliteConnecction.SqliteConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddController implements Initializable {

	@FXML private TextField nameT;
	@FXML private TextField surnameT;
	@FXML private TextField locT;
	@FXML private DatePicker dateOfBirth;
	@FXML private RadioButton sexM;
	@FXML private RadioButton sexF;
	@FXML private Button finish;
	@FXML private Label lblN;
	@FXML private Label lblS;
	@FXML private Label lblL;
	@FXML private Label lblCheck;
	@FXML private ChoiceBox<String> addToUser = new ChoiceBox<String>();

    @FXML private ToggleGroup sexToggleGroup = new ToggleGroup(); 
    
    private boolean num;
    private boolean num2;
    private boolean num3;
    
	private ObservableList<Person> dataList;
	private ReadUsers readUsers = new ReadUsers();

    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	
        sexM.setToggleGroup(sexToggleGroup);
        sexF.setToggleGroup(sexToggleGroup);
    	sexM.setUserData("Male");
    	sexF.setUserData("Female");
 
    	
		nameT.textProperty().addListener((observable, oldValue, newValue) -> {
            String newWordN = StringUtils.capitalize(newValue);
            nameT.setText(newWordN);
            
            if(StringUtils.isAlpha(newWordN) == false){
                lblN.setText("Only letters");
                num = true;
            } else {
            	num = false;
            }
		});
		
		surnameT.textProperty().addListener((observable, oldValue, newValue) -> {
            String newWordS = StringUtils.capitalize(newValue);
            surnameT.setText(newWordS);
            
            if(StringUtils.isAlpha(newWordS) == false){
                lblS.setText("Only letters");
                num2 = true;
            } else {
            	num2 = false;
            }
		});
		
		locT.textProperty().addListener((observable, oldValue, newValue) -> {
            String newWordC = StringUtils.capitalize(newValue);
            locT.setText(newWordC);
            
            if(StringUtils.isAlpha(newWordC) == false){
                lblL.setText("Only letters");
                num3 = true;
            } else {
            	num3 = false;
            }
		});
		
		
	    Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				readUsers.readUsersConnection();
			}
		});
		
		t1.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// getting users to the ChoiceBox
			readUser();
    }
    

    public void readUser() {
    	addToUser.getItems().addAll(readUsers.readUsers());
    }

    public ObservableList<Person> listAd(ObservableList<Person> dataList){

		 	Person two = new Person(
					nameT.getText(),
					surnameT.getText(),
					dateOfBirth.getEditor().getText(),
					sexToggleGroup.getSelectedToggle().getUserData().toString(),
					locT.getText(),
					addToUser.getSelectionModel().getSelectedItem()
					);

    	 changeDate(two);

    	 dataList.addAll(two);
		 return dataList;
    }
    
    
    public void changeDate(Person two){
    	int year = LocalDate.now().getYear();
    	int date = dateOfBirth.getValue().getYear();

    	two.setAge(String.valueOf(year - date));
    }
    
    @FXML
	public void btn1(ActionEvent event) throws Exception {
	 
		 TextField[] fieldList = {nameT, surnameT, locT};
     
		 boolean right = false;
		 boolean right2 = false;
	 
		 if(fieldList[0].getText().equals("") || fieldList[1].getText().equals("") || fieldList[2].getText().equals("") || dateOfBirth.getEditor().getText().equals("") || sexToggleGroup.getSelectedToggle() == null){
		    lblCheck.setText("All of places have to be filled");
		    right = true;
		 } 
		 if(num == true || num2 == true || num3 == true){
		 	 lblCheck.setText("Something is not correct");
		 	 right2 = true;
		 }	 
		
		 
		 if(right == false && right2 == false) {	 
			 
		 listAd(dataList);
		 
		 Stage stage = (Stage) finish.getScene().getWindow();
	     stage.close();
		 }
     }
	
	 
	public TextField getNameT() {
		return nameT;
	}

	public void setNameT(TextField nameT) {
		this.nameT = nameT;
	}

	public TextField getSurnameT() {
		return surnameT;
	}

	public void setSurnameT(TextField surnameT) {
		this.surnameT = surnameT;
	}

	public TextField getLocT() {
		return locT;
	}

	public void setLocT(TextField locT) {
		this.locT = locT;
	}

	public DatePicker getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DatePicker dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public RadioButton getSexM() {
		return sexM;
	}

	public void setSexM(RadioButton sexM) {
		this.sexM = sexM;
	}

	public RadioButton getSexF() {
		return sexF;
	}

	public void setSexF(RadioButton sexF) {
		this.sexF = sexF;
	}


	public ToggleGroup getSexToggleGroup() {
		return sexToggleGroup;
	}

	public void setSexToggleGroup(ToggleGroup sexToggleGroup) {
		this.sexToggleGroup = sexToggleGroup;
	}
	
	public void setDataList(ObservableList<Person> dataList) {
		this.dataList = dataList;
	}

	public void getDataList(ObservableList<Person> dataList) {
		
	}

}
