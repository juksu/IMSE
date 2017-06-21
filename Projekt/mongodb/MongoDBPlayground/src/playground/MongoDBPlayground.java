/**
 * This is a very simple mongodb example playground which could be usefull for future refernces
 * Source and some more information:
 *  - http://mongodb.github.io/mongo-java-driver/2.13/getting-started/quick-tour/
 *  - http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 */

package playground;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBPlayground {

	public static void main(String[] args) {

		/**
		 * Standard Login
		 */
		// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
		// if it's a member of a replica set:
		MongoClient mongoClient = null;
		try {
				mongoClient = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * getting Client if authentications is required
		 */
		// credentials for login if authentication is required 
		MongoCredential credential = MongoCredential.createCredential("eeBF_Kunde", "test", "Silverado".toCharArray());
		// get client
		try {
			mongoClient = new MongoClient(new ServerAddress( "localhost", 27017 ),
					Arrays.asList(credential));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * getting the database from client
		 */
		DB db = mongoClient.getDB( "test" );
		
		/**
		 * getting a collection in the database
		 */
		DBCollection coll = db.getCollection("testcoll");
		
		/**
		 * Get the first element in the collection
		 */
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
		
		/**
		 * Inserting Objects into the collection
		 */
		// make a BasicDBObject
		// here we have also an example of an embeded document (info)
		BasicDBObject newdbObject = new BasicDBObject("user", "javafoobar")
		        .append("type", "java")
		        .append("count", 1)
		        .append("info", new BasicDBObject("x", 203).append("y", 102));
		
		coll.insert( newdbObject );
		
		// alternative way to create a document 
		BasicDBObject document = new BasicDBObject();
		document.put("user", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());
		coll.insert(document);

		/**
		 * get all Documents in collection
		 */
		// get all Documents
		DBCursor cursor = coll.find();
		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		
		
		/**
		 * get a specific document in collection
		 */
		BasicDBObject query = new BasicDBObject("user", "foo");
		cursor = coll.find(query);
		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		
		
		/**
		 * creating a new collection
		 */
		db.createCollection("newCollection", null);
		DBCollection collnew = db.getCollection("newCollection");
		
		
		/**
		 * DBRef: Referencting documents in other collection
		 */
		// if we want a reference to a document of another collection we use DBRef
		// say we want a reference to the first document in testcoll
		// first get the document we want to have a refernce to.
		myDoc = coll.findOne();
		
		// now we create a new document and add the reference by DBRef
		// new DBRef("<collection>", "<document id>");
		document = new BasicDBObject();
		document.append("somedata", "fooref");
		document.append("reftosomething", new DBRef( "testcoll", myDoc.get("_id") ) );
		
		collnew.insert(document);

		// Bonus thing: for refs in mongodb shell
		// db.kunde.insert({"name":"foo", "status":"bar", testref:{"$ref":"benutzerkonto", "$id":ObjectId("594a9b1b4d6ed32b5c000001")} })
		
		
		/**
		 * close the client
		 */
		mongoClient.close();
		
		System.out.println("wow, i got till here");
	}
}
