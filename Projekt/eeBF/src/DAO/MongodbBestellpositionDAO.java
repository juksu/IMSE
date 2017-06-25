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
		System.out.println( "insertBestellposition mongodb" );
		
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
		BasicDBObject query = new BasicDBObject( "oid", order.getId() );
		
		System.out.println( "orderid " + order.getId() );
		
		DBObject orderObj = orderColl.findOne( query );
		
		if( orderObj == null )
		{
			System.err.println( "InsertBestellposition: order does not exist" );
			mongoClient.close();
			return; 
		}
		
		DBCollection productColl = db.getCollection( "produkt" );
		query = new BasicDBObject( "pid", item.getProduct().getId() );
		DBObject productObj = productColl.findOne( query );
		if( productObj == null )
		{
			System.err.println( "InsertBestellposition: product does not exist in database" );
			mongoClient.close();
			return;
		}
		
		BasicDBObject newItem = new BasicDBObject()
				.append( "produkt", new DBRef( "produkt", productObj.get( "_id" ) ) )		// REF to Product
				.append( "menge", item.getQuantity() )
				.append( "preisprostueck", item.getPricePerUnit() );
				
		
		BasicDBObject updateItems = new BasicDBObject().append( "bestellposition", newItem );
		
		BasicDBObject updatedOrderObj = new BasicDBObject()
				.append( "$set", updateItems );
		
		orderColl.update( orderObj, updatedOrderObj );
		
		mongoClient.close();
	}

	@Override
	public void updateBestellposition( Bestellung order, Bestellposition item )
	{
		// TODO Low priority: is not used in current use cases
	}

}
