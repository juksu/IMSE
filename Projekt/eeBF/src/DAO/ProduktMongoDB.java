package DAO;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import Model.Produkt;
import Model.Produktkategorie;

public class ProduktMongoDB implements IProduktDAO {

	private Produkt createProduktObject(DBObject obj) throws SQLException
	{
		int id = (int) obj.get("_id");
		String name=(String) obj.get("PBezeichnung");
		String beschreibung = (String) obj.get("PBeschreibung");
		Float price = (Float) obj.get("preis");
		int quantity = (int) obj.get("Menge");
		// TODO Oberkategorie für jetzt null (jede Kategorie ist die Wurzel)
		Produkt p = new Produkt (id, name, beschreibung, price, quantity, null);
		return p;
	}	
	
	
	@Override
	public void createProdukt(int id, String name, String description, int price, int quantity, int lagerid) {
		MongoClient mongoClient = null;
		try
		{
			mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.ADMIN );
		} catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
			
		DB db = DBConnection.getMongoDBConnection( mongoClient );
		
		
		DBCollection coll = db.getCollection("produkt");
		BasicDBObject query = new BasicDBObject( "_id", new ObjectId() )
		.append( "PBezeichnung", name )
		.append( "PBeschreibung", description )
		//.append( "price", null );  ?? price ist nicht in mongoDB definiert 
		.append( "Menge", quantity )
		.append( "sid", lagerid );
		
		coll.insert(query);
		
		mongoClient.close();
				

	}
	
	public int MengePruefen(String titel){
MongoClient mongoClient = null;
		
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			DBCollection coll = db.getCollection("produkt");
			
			
			BasicDBObject query = new BasicDBObject();
			 query.put("PBezeichnung", titel);
			 DBCursor cursor =coll.find(query);
			 DBObject obj;
			 boolean Titel=cursor.hasNext();
			int menge= 0;
			 while(Titel)
			  { 
			    obj=cursor.next();        
			     menge = (int) obj.get("Menge");
			    
			  }
			  return menge;  
	}

	
	

	@Override
	public int getAnzahl() {
		MongoClient mongoClient = null;
		int counter = 0;
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.ADMIN );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			
			DBCollection coll = db.getCollection("produkt");
			DBCursor cursor = coll.find();
			while(cursor.hasNext()) {
			       counter++;
			   }

		return counter;
		
	}

	@Override
	public String getBezeichnung(int id) {
		MongoClient mongoClient = null;
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.ADMIN );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			DBCollection coll = db.getCollection("produkt");
			String idString = Integer.toString(id);
			/*
			DBObject searchById = new BasicDBObject("kid", new ObjectId(idString));
			DBObject found = coll.findOne(searchById);
			String name = null;
			*/
			BasicDBObject queryDetails = new BasicDBObject();
			 queryDetails.put("_id", idString);
			 DBCursor cursorDetails =coll.find(queryDetails);
			 DBObject oneDetails;
			 boolean Name=cursorDetails.hasNext();
			 String name = null;
			 while(Name)
			  { 
			    oneDetails=cursorDetails.next();        
			     name = oneDetails.get("PBezeichnung").toString();
			    
			  }
			  return name;  
			
	}

	@Override
	public ArrayList<Produkt> getAllProdukten() {
		MongoClient mongoClient = null;
		
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.ADMIN );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			MongoCollection<DBObject> collection = (MongoCollection<DBObject>) db.getCollection("produkt");
			
			BasicDBObject queryDetails = new BasicDBObject();
			
			List<DBObject> produkten = collection.find().into(
					new ArrayList<DBObject>());
			List<Produkt> pr = new ArrayList<Produkt>();
			
			for (DBObject p : produkten){
				try {
					pr.add(createProduktObject(p));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return (ArrayList<Produkt>) pr;
	}

	@Override
	public ArrayList<Produkt> getAllProduktenByLagerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newMenge(int pid, int newmenge) {
		// TODO Auto-generated method stub

	}

	@Override
	public Produkt getProduktById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
