package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	enum connectionTypes{USER, CUSTOMER, ADMIN};
	
	private String dbPath;
	private String dbUser;
	private String dbPassword;
	
	Connection getConnection(connectionTypes connectionType) throws SQLException, ClassNotFoundException
	{
		// TODO A connection pool would be nice to have
		
		dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
		Class.forName("com.mysql.jdbc.Driver");
		
		// TODO one does not simply hardcode database access data and share them on github
		// not to speak of having unsafe passwords
		// this is madness!!
		// arghlbarghlgah!!!
		switch( connectionType )
		{
		case USER : 
			dbUser = "eeBF_Benutzer";
			dbPassword = "Unforgiven";
			break;
		case CUSTOMER :
			dbUser = "eeBF_Kunde";
			dbPassword = "Silverado";
			break;
		case ADMIN : 
			dbUser = "eeBF_Admin";
			dbPassword = "Tombston";
			break;
		}
		return DriverManager.getConnection(dbPath, dbUser, dbPassword);
	}
}
