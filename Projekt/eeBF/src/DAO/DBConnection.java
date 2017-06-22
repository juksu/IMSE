package DAO;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

class DBConnection {
	static enum userTypes{USER, CUSTOMER, ADMIN};
	
	static Connection getMySQLConnection( userTypes userType ) throws SQLException, ClassNotFoundException
	{
		// TODO A connection pool would be nice to have
		String dbUser;
		String dbPassword;
		// devolpment env
		// String dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
		// production env
		String dbPath  = "jdbc:mysql://www.eeBF.at:3306/eebf?useSSL=false";
		
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
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbPath, dbUser, dbPassword);
	}
	
	static DB getMongoDBConnection( userTypes userType ) throws UnknownHostException
	{
		// TODO A connection pool would be nice to have
		String dbUser;
		String dbPassword;
		// development env
		String serverAddress = "localhost";
		int port = 27017;
		// production env
//		String serverAddress = "www.eeBF.at";
		String collection = "eebf";
		
		
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
		
		MongoClient mongoClient;
		// get credentials for authentication
		MongoCredential credential = MongoCredential.createCredential(
				dbUser, collection, dbPassword.toCharArray() );
		// get client
		mongoClient = new MongoClient(new ServerAddress( serverAddress, port ), 
				Arrays.asList(credential));

		// get database from client
		return mongoClient.getDB( collection );
	}
}
