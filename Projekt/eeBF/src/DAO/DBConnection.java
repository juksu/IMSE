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

import conf.Conf;

class DBConnection {
	static enum userTypes{USER, CUSTOMER, ADMIN};
	
	static Connection getMySQLConnection( userTypes userType ) throws SQLException, ClassNotFoundException
	{
		// TODO A connection pool would be nice to have
		String dbUser;
		String dbPassword;
		String host = Conf.getHost();
		String port = "3306";
		// devolpment env
		// String dbPath = "jdbc:mysql://localhost:3306/eebf?useSSL=false";
		// production env
//		String dbPath  = "jdbc:mysql://www.eeBF.at:3306/eebf?useSSL=false";
		
		String dbPath = "jdbc:mysql://".concat(host).concat(":").concat(port)
				.concat("/eebf?useSSL=false");
		
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
				dbUser = "eeBF_Kunde";		// NOTIZE: eeBF_Kunde has readwrite role but this seem not to be sufficient for mongodb jar files
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
	
	static MongoClient getMongoClient( userTypes userType ) throws UnknownHostException
	{
		// TODO A connection pool would be nice to have
		String dbUser;
		String dbPassword;
		// development env
		String host = Conf.getHost();
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
		mongoClient = new MongoClient(new ServerAddress( host, port ), 
				Arrays.asList(credential));

		return mongoClient;
	}
	
	
	static DB getMongoDBConnection( MongoClient mongoClient ) throws NullPointerException
	{
		if( mongoClient == null )
			throw( new NullPointerException() );

		String dbs = "eebf";
		
		// get database from client
		return mongoClient.getDB( dbs );
	}
}
