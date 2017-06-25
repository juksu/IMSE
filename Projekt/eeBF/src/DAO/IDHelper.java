package DAO;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Class that keeps track of the current ids and its function return a unique id
 * Only works if every document in database this function for its respective collection was called
 */
class IDHelper
{
	private static enum identifiers{maxBestellungID, 
			maxBestellpositionID, maxLagerID, maxProduktID, 
			maxProduktkategorieID, maxUserID};
	
	private static int maxBestellungID = 0;
	private static int maxBestellpositionID = 0;
	private static int maxLagerID = 0;
	private static int maxProduktID = 0;
	private static int maxProduktkategorieID = 0;
	private static int maxUserID = 0;
	
	private static int getIdentifier( identifiers id ){
		MongoClient mongoClient = null;
		try
		{
			mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
		} catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
		
		DB db = DBConnection.getMongoDBConnection( mongoClient );
		
		DBCollection idColl = db.getCollection( "identifiers" );
		
		// there is only one document in this collection
		DBObject idObj =  idColl.findOne();
		
		int returnvalue = 0;
		if( idObj == null )
		{
			maxBestellungID = 0;
			maxBestellpositionID = 0;
			maxLagerID = 0;
			maxProduktID = 0;
			maxProduktkategorieID = 0;
			maxUserID = 0;
			
			BasicDBObject insertObj = new BasicDBObject()
					.append( "maxBestellungID", 0 )
					.append( "maxBestellpositionID", 0 )
					.append( "maxLagerID", 0 )
					.append( "maxProduktID", 0 )
					.append( "maxProduktkategorieID", 0 )
					.append( "maxUserID", 0 );
					
					
			idColl.insert( insertObj );
			
			// just get the idObj again
			idObj =  idColl.findOne();
		}	

		maxBestellungID = (int) idObj.get( "maxBestellungID" );
		maxBestellpositionID = (int) idObj.get( "maxBestellpositionID" );
		maxLagerID = (int) idObj.get( "maxLagerID" );
		maxProduktID = (int) idObj.get( "maxProduktID" );
		maxProduktkategorieID = (int) idObj.get( "maxProduktkategorieID" );
		maxUserID = (int) idObj.get( "maxUserID" );
			
		switch( id )
		{
			case maxBestellungID :
				maxBestellungID = (int) idObj.get( "maxBestellungID" );
				maxBestellungID++;
				returnvalue = maxBestellungID;
				break;
			case maxBestellpositionID :
				maxBestellpositionID = (int) idObj.get( "maxBestellpositionID" );
				maxBestellpositionID++;
				returnvalue = maxBestellpositionID;
				break;
				// TODO
		}
		
		BasicDBObject insertObj = new BasicDBObject()
				.append( "maxBestellungID", maxBestellungID )
				.append( "maxBestellpositionID", maxBestellpositionID )
				.append( "maxLagerID", maxLagerID )
				.append( "maxProduktID", maxProduktID )
				.append( "maxProduktkategorieID", maxProduktkategorieID )
				.append( "maxUserID", maxUserID );
			
		idColl.update( idObj, insertObj );
		mongoClient.close();
		
		return returnvalue;
	}
	
	public static int getUniqueBestellungID(){
		return getIdentifier( identifiers.maxBestellungID );
	}
	
	public static int getUniqueBestellpositionID(){
		return getIdentifier( identifiers.maxBestellpositionID );
	}
	
	public static int getUniqueLagerID(){
		return getIdentifier( identifiers.maxLagerID );
	}
	
	public static int getUniqueProduktID(){
		return getIdentifier( identifiers.maxProduktID );
	}
	
	public static int getUniquProduktkategorieID(){
		return getIdentifier( identifiers.maxProduktkategorieID );
	}
	
	public static int getUniquUserID(){
		return getIdentifier( identifiers.maxUserID );
	}
}
