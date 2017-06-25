package DAO;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;

import Model.Bestellposition;
import Model.Bestellung;

public class MongodbBestellpositionDAO implements IBestellpositionDAO
{

	@Override
	public void insertBestellposition( Bestellung order, Bestellposition item )
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
		
		DBCollection orderColl = db.getCollection( "bestellung" );
		BasicDBObject query = new BasicDBObject( "_id", order.getCustomer().getId() );
		
		DBObject orderObj = orderColl.findOne( query );
		
		
		if( orderObj == null )
		{
			System.err.println( "InsertBestellung: order does not exist" );
			mongoClient.close();
			return; 
		}
		
		BasicDBObject newItem = new BasicDBObject()
				.append( "produkt", new DBRef( "produkt", Long.toHexString( item.getProduct().getId() ) ) )		// REF to Product
				.append( "menge", item.getQuantity() )
				.append( "preisprostueck", item.getPricePerUnit() );
				
		
		BasicDBObject updateItems = new BasicDBObject().append( "bestellposition", newItem );
		
		BasicDBObject updatedOrderObj = new BasicDBObject()
				.append( "$set", updateItems );
		
		query = new BasicDBObject( "_id", order.getId() );
		
		orderColl.update( query, updatedOrderObj );

		mongoClient.close();
	}

	@Override
	public void updateBestellposition( Bestellung order, Bestellposition item )
	{
		// TODO
	}

}
