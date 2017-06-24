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

public class ProduktkategorieMongoDB implements IProduktkategorieMongoDB {

	
	int kategorieId = 0;
	
	
	
	
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
		/*
		int counter = 0;
		try{
			DB db = openConnection();
			System.out.println("connected successfully");
			
			DBCollection coll = db.getCollection("produktkategorie");
			DBCursor cursor = coll.find();
			while(cursor.hasNext()) {
			       counter++;
			   }

			
			
			}catch(Exception e){
				System.out.println(e);
			}
		return counter;
		*/
		return null;
	}
	

	@Override
	public String getName(int id) {
		try{
			DB db = openConnection();
			System.out.println("connected successfully");
			
			DBCollection coll = db.getCollection("produktkategorie");
			
			
			}catch(Exception e){
				System.out.println(e);
			}
		return null;
		
	}

	@Override
	public String getBeschreibung(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produktkategorie> getAllProduktkategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIdByName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}
