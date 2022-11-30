package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
	
	
	private static  String HOST = "localhost";
	private static  String PASSWORD= "Test123!";
	private static  int PORT = 3306;
	private static  String SCHEMA= "projects";//ghide
	private static  String USER = "root";
	
public static Connection getConnection() {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
		//Connecting with url=jdbc:mysql:localhost:3306/Mysql?user=root&password=Test123!&useSSL=false
		System.out.println("Connecting with url=" + url);
		
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection to schema '" + SCHEMA + "' is successful.");
			return conn;
			
		} catch (SQLException e) {
			System.out.println("Unable to get connection at " + url);
			throw new DbException("Unable to get connection at \" +uri");
		}
	}
}

