package application.loginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

import application.sqliteConnecction.SqliteConnection;

public class LoginModel {
	
	private Connection conection; 
	private String userName = "";
	private String[] tab = new String[4];

	
	public void logConnection() {
		conection = SqliteConnection.Connector();
		if(conection == null){
			System.out.println("Not Connected");
			System.exit(1);
		}		
	}

	public boolean isDbConnected() {
		try {
			return !conection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String login, String password) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from User where login = ? and password = ?";
		
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, DigestUtils.md5Hex(login).toUpperCase());
			preparedStatement.setString(2, DigestUtils.md5Hex(password).toUpperCase());
			
			resultSet = preparedStatement.executeQuery();
			userName = (String) resultSet.getObject(1);
			tab[0] = resultSet.getString(1);
			tab[1] = resultSet.getString(2);
			tab[2] = resultSet.getString(3);
			tab[3] = resultSet.getString(4);
			if(resultSet.next()){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public String getUser(){
		return userName;
	}

	public String[] getTab() {
		return tab;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}
	
	
}	
