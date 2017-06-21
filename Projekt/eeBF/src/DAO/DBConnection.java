package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	static enum dbTypes{MYSQL, MONGODB};
	static enum userTypes{USER, CUSTOMER, ADMIN};
	
	static Connection getConnection( dbTypes dbType, userTypes userType ) throws SQLException, ClassNotFoundException
	{
		// TODO A connection pool would be nice to have
		
		String dbUser;
		String dbPassword;
		String dbPath;
		
		// TODO one does not simply hardcode database access data and share them on github
		// not to speak of having unsafe passwords
		// this is madness!!
		// arghlbarghlgah!!!
		switch( userType )
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
		
		switch( dbType )
		{
			case MYSQL :
				// devolpment env
				// String dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
				// production env
				dbPath = "jdbc:mysql://www.eeBF.at:3306/eebf?useSSL=false"; // TODO set to this path on production
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection(dbPath, dbUser, dbPassword);
			case MONGODB :
				dbPath = ""; // TODO
				
			default :
				return null;
		}
		
		
	}
}
