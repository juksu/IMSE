package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	
	private String dbPath;
	private String dbUser;
	private String dbPassword;
	
	Connection getConnection() throws SQLException, ClassNotFoundException
	{
		dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
		dbUser = "eeBF_Admin";
		dbPassword = "Tombstone";

		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(dbPath, dbUser, dbPassword);
	}
}
