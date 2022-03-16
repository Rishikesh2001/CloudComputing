package com.login;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	String url = "jdbc:mysql://localhost:3306/demo";
	String username = "root";
	String password = "Divya@143";	
	String query = "SELECT * from Login WHERE username=? AND pass=?";
	public String display() {
		String var = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url, username, password);
			
			PreparedStatement statement = connect.prepareStatement("SELECT * from Login");
			
			ResultSet set = statement.executeQuery();
			var = "<table><tr><th>Username</th><th>Password</th></tr>";
			while(set.next()) {
				var += "<tr><th>"+set.getString(1)+"</th><th>"+set.getString(2)+"</th></tr>";
			}
			var += "</table>";
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return var;
	}
	
	public boolean check(String user, String pass) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url, username, password);
			
			PreparedStatement statement = connect.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, pass);
			
			ResultSet set = statement.executeQuery();
			if(set.next()) {
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
}
