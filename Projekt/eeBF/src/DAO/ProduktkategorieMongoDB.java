package DAO;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Model.Produktkategorie;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import DAO.DBConnection.userTypes;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

public class ProduktkategorieMongoDB implements IProduktkategorieDAO {

	
	int kategorieId = 0;
	
	
	public ProduktkategorieMongoDB(){
		System.out.println("Konstruktor aufruf");
	}
	
	private Produktkategorie createProduktKatObject(DBCursor rs) throws SQLException
	{
		
		int id = ((ResultSet) rs).getInt("kid");
		String titel=((ResultSet) rs).getString("bezeichnung");
		String beschreibung = ((ResultSet) rs).getString("beschreibung");
		// TODO Oberkategorie für jetzt null (jede Kategorie ist die Wurzel)
		Produktkategorie pK = new Produktkategorie (id, titel, beschreibung, null);
		return pK;
	}	
	
	public void createProduktkategorie(String name, String beschreibung) {
		
		MongoClient mongoClient = null;
		try
		{
			mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
		} catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
			
		DB db = DBConnection.getMongoDBConnection( mongoClient );
		
		
		DBCollection coll = db.getCollection("produktkategorie");
		BasicDBObject query = new BasicDBObject( "kid", new ObjectId() )
		.append( "bezeichnung", name )
		.append( "beschreibung", beschreibung )
		.append( "oberkategorie", null );
		
		coll.insert(query);
		
		mongoClient.close();
				
	}

	@Override
	public int getAnzahl() {
		MongoClient mongoClient = null;
		int counter = 0;
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			
			DBCollection coll = db.getCollection("produktkategorie");
			DBCursor cursor = coll.find();
			while(cursor.hasNext()) {
			       counter++;
			   }

		return counter;
		
	}
	

	@Override
	public String getName(int id) {
		MongoClient mongoClient = null;
		int counter = 0;
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			DBCollection coll = db.getCollection("produktkategorie");
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
			     name = oneDetails.get("bezeichnung").toString();
			    
			  }
			  return name;  
			
	}

	@Override
	public String getBeschreibung(int id) {
		MongoClient mongoClient = null;
		int counter = 0;
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			DBCollection coll = db.getCollection("produktkategorie");
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
			     name = oneDetails.get("beschreibung").toString();
			    
			  }
			  return name;  
			
	}
//Lena
	@Override
	public ArrayList<Produktkategorie> getAllProduktkategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIdByName(String name) {
		MongoClient mongoClient = null;
		
		try
			{
				mongoClient = DBConnection.getMongoClient( DBConnection.userTypes.CUSTOMER );
			} catch( UnknownHostException e )
			{
				e.printStackTrace();
			}
				
			DB db = DBConnection.getMongoDBConnection( mongoClient );
			
			DBCollection coll = db.getCollection("produktkategorie");
			
			/*
			DBObject searchById = new BasicDBObject("kid", new ObjectId(idString));
			DBObject found = coll.findOne(searchById);
			String name = null;
			*/
			BasicDBObject queryDetails = new BasicDBObject();
			 queryDetails.put("bezeichnung", name);
			 DBCursor cursorDetails =coll.find(queryDetails);
			 DBObject oneDetails;
			 boolean Name=cursorDetails.hasNext();
			int id= 0;
			 while(Name)
			  { 
			    oneDetails=cursorDetails.next();        
			     id = (int) oneDetails.get("kid");
			    
			  }
			  return id;  
	}

}
