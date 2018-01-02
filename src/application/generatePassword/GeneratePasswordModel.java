package application.generatePassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import application.sqliteConnecction.SqliteConnection;

public class GeneratePasswordModel {

	private Connection conn;
	private int pass;
	private String user;
	private String login;
	private String ifFind = "";
	
	
	public void generatorPasswordModelConnection() {
		conn = SqliteConnection.Connector();
		if(conn == null){
			System.exit(1);
		}
	}
	
	public void getPass(String uniqueWord) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select * from User where UniqueWord = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, DigestUtils.md5Hex(uniqueWord).toUpperCase());
			
			resultSet = preparedStatement.executeQuery();

			user = resultSet.getString("User");
			login = resultSet.getString("Login");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ifFind = e.getMessage();
		} finally {
			resultSet.close();
			preparedStatement.close();
		}
	}
	
	public void setPass(String uniqW) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		String query2 = "update User set User ='"+user+"',"
				+ "Login ='"+login+"',"
						+ "Password ='"+DigestUtils.md5Hex(String.valueOf(pass)).toUpperCase()+"',"
								+ "UniqueWord = '"+DigestUtils.md5Hex(String.valueOf(uniqW)).toUpperCase()+"' "
										+ "where User ='"+user+"'";
		
		try {
			preparedStatement = conn.prepareStatement(query2);
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			preparedStatement.close();
		}
	}
	
	protected int randomPass(){

		Random ran = new Random();
		pass = ran.nextInt(999999);
		return pass;
	}

	public int getPass() {
		return pass;
	}

	public String getIfFind() {
		return ifFind;
	}
	
	
}
