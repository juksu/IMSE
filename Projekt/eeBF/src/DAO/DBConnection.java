package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	static enum connectionTypes{USER, CUSTOMER, ADMIN};
	
	static Connection getConnection(connectionTypes connectionType) throws SQLException, ClassNotFoundException
	{
		// TODO A connection pool would be nice to have
		
		String dbUser;
		String dbPassword;
//		String dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
		String dbPath = "jdbc:mysql://www.eeBF.at:3306/eebf?useSSL=false"; // TODO set to this path on production
		
		Class.forName("com.mysql.jdbc.Driver");
		
		// TODO one does not simply hardcode database access data and share them on github
		// not to speak of having unsafe passwords
		// this is madness!!
		// arghlbarghlgah!!!
		switch( connectionType )
		{
			case ADMIN : 
				dbUser = "eeBF_Admin";
				dbPassword = "Tombstone";
				break;
			case CUSTOMER :
				dbUser = "eeBF_Kunde";
				dbPassword = "Silverado";
				break;
			case USER :
			default : 
				dbUser = "eeBF_Benutzer";
				dbPassword = "Unforgiven";
				break;
		}
		return DriverManager.getConnection(dbPath, dbUser, dbPassword);
	}
}
