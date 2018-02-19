package application.Main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Exception;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.Person.Person;
import application.addController.AddController;
import application.changeUserPassword.ChangeUserPasswordController;
import application.searchPerson.SearchPersonController;




public class MainController implements Initializable {
	
	@FXML private MenuBar allMenu;
	@FXML private Menu fileMenu;
	@FXML private Menu userMenu;
	@FXML private MenuItem addF;
	@FXML private MenuItem editF;
	@FXML private MenuItem deleteF;
	@FXML private MenuItem logOut;
	@FXML private MenuItem addProf;
	@FXML private Label hellolbl;
	@FXML private MenuItem logOutButton;
	
	
	@FXML private TableView<Person> personTable = new TableView<Person>();
	@FXML private TableColumn<Person, String> nameCol;
	@FXML private TableColumn<Person, String> surnameCol;
	@FXML private TableColumn<Person, String> ageCol;
	@FXML private TableColumn<Person, String> sexCol;
	@FXML private TableColumn<Person, String> locCol;
	
	@FXML private ObservableList<Person> dataList = FXCollections.observableArrayList();
		
	private String jsonObject;
	private String userNameCheck;
	private String[] tab;
	
	
	  @Override
	  public void initialize(URL location, ResourceBundle resources){
			
		    nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
		    nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		    nameCol.setOnEditCommit(
		        new EventHandler<CellEditEvent<Person, String>>() {
		            @Override
		            public void handle(CellEditEvent<Person, String> t) {
		                ((Person) t.getTableView().getItems().get(
		                    t.getTablePosition().getRow())
		                    ).setName(t.getNewValue());
		            }
		        }
		    );
		    surnameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Surname"));
		    surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		    surnameCol.setOnEditCommit(
		        new EventHandler<CellEditEvent<Person, String>>() {
		            @Override
		            public void handle(CellEditEvent<Person, String> t) {
		                ((Person) t.getTableView().getItems().get(
		                    t.getTablePosition().getRow())
		                    ).setSurname(t.getNewValue());
		            }
		        }
		    );
		    ageCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Age"));
		    ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
		    ageCol.setOnEditCommit(
		        new EventHandler<CellEditEvent<Person, String>>() {
		            @Override
		            public void handle(CellEditEvent<Person, String> t) {
		                ((Person) t.getTableView().getItems().get(
		                    t.getTablePosition().getRow())
		                    ).setAge(t.getNewValue());
		            }
		        }
		    );
		    sexCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Sex"));
		    sexCol.setCellFactory(TextFieldTableCell.forTableColumn());
		    sexCol.setOnEditCommit(
		        new EventHandler<CellEditEvent<Person, String>>() {
		            @Override
		            public void handle(CellEditEvent<Person, String> t) {
		                ((Person) t.getTableView().getItems().get(
		                    t.getTablePosition().getRow())
		                    ).setSex(t.getNewValue());
		            }
		        }
		    );
		    locCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Location"));
		    locCol.setCellFactory(TextFieldTableCell.forTableColumn());
		    locCol.setOnEditCommit(
		        new EventHandler<CellEditEvent<Person, String>>() {
		            @Override
		            public void handle(CellEditEvent<Person, String> t) {
		                ((Person) t.getTableView().getItems().get(
		                    t.getTablePosition().getRow())
		                    ).setLocation(t.getNewValue());
		            }
		        }
		    );

			personTable.setEditable(true);
			personTable.setItems(dataList);
	  }	
	  
	  public void saveJson(ObservableList<Person> dataList, String jsonObject) throws IOException{

		 Gson gson = new Gson();
		 jsonObject = gson.toJson(dataList);
		  		
		 FileWriter writer = new FileWriter("listOfPeople.json");
		 writer.write(jsonObject);
		 writer.close();
	 }
	  
	  
	 private void readJson(ObservableList<Person> dataList, String jsonObject) {
		
		 try {
			  FileReader reader = new FileReader("listOfPeople.json");
		 			
		 	  Type type = new TypeToken<ObservableList<Person>>(){}.getType();
		 	  ArrayList<Person> list = new Gson().fromJson(reader, type);
 
		 	  for(Person read : list){
		 		  if(read.getUser().equals(userNameCheck)){
		 			 dataList.add(read);
		 		  }
		 	  }
	  
		 	  reader.close();	
			  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			  hellolbl.setText("Can't find a file");
			  }			 
		 }
	 	 
	 public void getUserName(String userName){
		 hellolbl.setText("Hello " + userName);
		 userNameCheck = userName;
	 }
		 
	 @FXML
	 public void addA(ActionEvent event) throws Exception {
	           
		 		Stage primaryStage2 = new Stage();
	            
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("/application/addController/Add.fxml"));
	            Pane root2 = (Pane) loader.load();
	            
				AddController addController = loader.getController();				
				addController.setDataList(dataList);
				addController.getDataList(dataList);

				Scene scene2 = new Scene(root2);
				scene2.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage2.setScene(scene2);
				primaryStage2.setTitle("Add Person");
				primaryStage2.show();
		 }

	 
	 @FXML
	 public void removeA(ActionEvent event) throws Exception {
		 Person selected = personTable.getSelectionModel().getSelectedItem();
		 dataList.remove(selected);
	 }
	 
	  	
	 @FXML
	 public void saveListA(ActionEvent event) throws IOException {
		 	
		 try {	
			  saveJson(dataList, jsonObject);	
			  } catch (IOException e) {
			   e.printStackTrace();
			  }

		    Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Save Information");
		    alert.setHeaderText(null);
		    alert.setContentText("Saved");
		    alert.showAndWait();
	 }


	 @FXML
	 public void readList(ActionEvent event) throws Exception {
		 readJson(dataList, jsonObject);
	 }
	 
	 
	 @FXML
	 public void searchPersonA(ActionEvent event) throws IOException {
		 
         	Stage primaryStage4 = new Stage();
            FXMLLoader loader4 = new FXMLLoader();
			loader4.setLocation(getClass().getResource("/application/searchPerson/SearchPerson.fxml"));
			TitledPane root4 = (TitledPane) loader4.load();
			SearchPersonController searchPersonController = loader4.getController();
			searchPersonController.setDataList(dataList);
			searchPersonController.getDataList(dataList);
			
			searchPersonController.getPersonTable(personTable);
			searchPersonController.setPersonTable(personTable);
			
			Scene scene4 = new Scene(root4);
			scene4.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage4.setScene(scene4);
			primaryStage4.setTitle("Search Person");
			primaryStage4.show();
	 }
 
	 
	 @FXML
	 public void logOut(ActionEvent event) throws IOException {
		 
				 Alert alert = new Alert(AlertType.CONFIRMATION);
				 alert.setTitle("Log Out");
				 alert.setHeaderText(null);
				 alert.setContentText("Are you sure to Log Out?");
		
				 ButtonType buttonTypeLogOut = new ButtonType("Log Out");
				 ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				 	
				 alert.getButtonTypes().setAll(buttonTypeLogOut, buttonTypeCancel);
		
				 Optional<ButtonType> result = alert.showAndWait();
				 if (result.get() == buttonTypeLogOut){
					 
					 	Stage stage = (Stage) hellolbl.getScene().getWindow();
					 	stage.close();
					 	
			         	Stage primaryStage6 = new Stage();
						Parent root6 = FXMLLoader.load(getClass().getResource("/application/loginController/Login.fxml"));
						Scene scene6 = new Scene(root6);
						scene6.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
						primaryStage6.setScene(scene6);
						primaryStage6.setTitle("Login");
						primaryStage6.show();
				 }
	 }
	 
	 @FXML
	 public void addUser(ActionEvent event) throws IOException {
     		
		 	Stage primaryStage7 = new Stage();
			Parent root7 = FXMLLoader.load(getClass().getResource("/application/addUserController/addUser.fxml"));
			Scene scene7 = new Scene(root7);
			scene7.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage7.setScene(scene7);
			primaryStage7.setTitle("Add User");
			primaryStage7.show();
	 }
	 
	 @FXML
	 public void changeMyPassword(ActionEvent event) throws IOException {
		 
		 	Stage primaryStage8 = new Stage();
		    FXMLLoader loader8 = new FXMLLoader();
		    Pane root8 = loader8.load(getClass().getResource("/application/changeUserPassword/ChangeUserPassword.fxml").openStream());
			ChangeUserPasswordController us = (ChangeUserPasswordController)loader8.getController();
			us.setTab(getTab());	
		    Scene scene8 = new Scene(root8);
			scene8.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage8.setScene(scene8);
			primaryStage8.setTitle("Change User Password");
			primaryStage8.show();
	 }
	 
	 @FXML
	 public void closeApp(ActionEvent event) throws IOException {
		 Platform.exit();
	 }
	 
	 
	public ObservableList<Person> getDataList() {
		return dataList;
	}

	public void setDataList(ObservableList<Person> dataList) {
		this.dataList = dataList;
	}

	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public TableView<Person> getPersonTable() {
		return personTable;
	}

	public void setPersonTable(TableView<Person> personTable) {
		this.personTable = personTable;
	}

	public String[] getTab() {
		return tab;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}
	
	
}
