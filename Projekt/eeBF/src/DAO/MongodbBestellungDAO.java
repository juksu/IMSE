package DAO;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;

import Model.Bestellung;

public class MongodbBestellungDAO implements IBestellungDAO
{
	// todo, the same as in mysql -> merge or have it in Bestellung?
	private String getBestellstatusString( Bestellung order )
	{
		StringBuilder sb = new StringBuilder();
		
		if( order.isOrderStateOrdered() )
			sb.append( "bestellt" );

		if( order.isOrderStatePaid() )
			sb.append( ",bezahlt" );

		if( order.isOrderStateSending() )
			sb.append( ",liefernd" );
		if( order.isOrderStateSent() )
			sb.append( ",geliefert" );
			
		if( order.isOrderStateComplete() )
			sb.append( ",abgeschlossen" );

		return sb.toString();
	}
	
	@Override
	public void insertBestellung( Bestellung order )
	{
		
		MongoClient mongoClient = null;
		try
		{
			mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
		} catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
		
		DB db = DBConnection.getMongoDBConnection( mongoClient );
		
		// Maybe MongodbUserDAO could help?
		// TODO: is the custumer id the id in database - it is true for mysql but now also for mongodb
		DBCollection kundeColl = db.getCollection( "benutzerkonto" );
		BasicDBObject query = new BasicDBObject( "_id", order.getCustomer().getId() );
		// there should be only one custumer with the id
		DBObject kundeObj =  kundeColl.findOne( query );
		
		if( kundeObj == null )
		{
			System.err.println( "InsertBestellung: customer does not exist" );
			mongoClient.close();
			return; 
		}
		
		DBCollection bestColl = db.getCollection( "bestellung" );
		BasicDBObject newBestObj = new BasicDBObject("_id", new ObjectId() )
				.append( "datum", new Date() )
				.append( "bestellstatus", getBestellstatusString( order ) )
				.append( "paypalTNr", order.getPaypalTNr() )
				.append( "kunde", new DBRef( "benutzerkonto", kundeObj.get( "_id" ) ) );
				
		bestColl.insert( newBestObj );

		mongoClient.close();
	}
	
	
	@Override
	public void updateBestellung( Bestellung order )
	{
		MongoClient mongoClient = null;
		try
		{
			mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
		} catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
		
		DB db = DBConnection.getMongoDBConnection( mongoClient );
		
		DBCollection bestColl = db.getCollection( "bestellung" );
		
		BasicDBObject fieldsToUpdate = new BasicDBObject()
				.append( "datum", order.getDate() )
				.append( "bestellstatus", getBestellstatusString( order ) )
				.append( "paypalTNr", order.getPaypalTNr() );
		
		BasicDBObject newBestObj = new BasicDBObject();
		newBestObj.append( "$set", fieldsToUpdate );
		
		/**
		 * TODO
		 */
		// parses a hexadecimal string to long
//		long id = Long.parseLong( "ffs", 16 );
		// parses the long value back into a hexadecimal string
//		String _id = Long.toHexString( id );
		
		BasicDBObject query = new BasicDBObject( "_id", order.getId() );
		
		bestColl.update( query, newBestObj );

		mongoClient.close();
	}
}
