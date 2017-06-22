package DAO;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Produktkategorie;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Set;

public class ProduktkategorieMongoDB implements IProduktkategorieMongoDB {

	
	private String userName = "eeBF_Admin";
	private String database= "eebf"; //Datenbankname
	private char[] password = {'T','o','m','b','s','t','o','n','e'};
	
	public ProduktkategorieMongoDB() 
	{
		MongoCredential credential = MongoCredential.createCredential(userName, database, password);
		MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
	}
	
	
	

	
	
	public void createProduktkategorie(String name, String beschreibung) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAnzahl() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
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
