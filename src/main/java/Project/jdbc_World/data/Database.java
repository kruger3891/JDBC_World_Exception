package Project.jdbc_World.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static final String URL = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC"; 
	private static final String USER = "root";
	private static final String PASS = "12121212";
	
	public static Connection getConnection ()throws SQLException {
		
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
