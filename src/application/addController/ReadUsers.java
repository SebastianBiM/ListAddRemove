package application.addController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.sqliteConnecction.SqliteConnection;


public class ReadUsers {
	
	// Getting name of users from sqlite to the Choicebox (when user's adding a person to the list)
	
	private Connection conn;
	
	ArrayList<String> read = new ArrayList<String>();
	
    public void readUsersConnection(){
		conn = SqliteConnection.Connector();
		if(conn == null){
			System.exit(1);
		}
    }

    
	public ArrayList<String> readUsers(){
		
		String query2 = "select User from User";
		ResultSet resultSet2 = null;
		PreparedStatement preparedStatement2 = null;
		
		try {
			//Connection conn = this.connect();
			preparedStatement2 = conn.prepareStatement(query2);
	        resultSet2 = preparedStatement2.executeQuery();
	        
			while(resultSet2.next()){
				read.add(resultSet2.getString("User"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return read;
	}
	
}
