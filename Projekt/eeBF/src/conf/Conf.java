package conf;

public class Conf {
	// available hosts: ["localhost", "www.eebf.at"]
	// available databases: ["mysql", "mongodb"]
	public static enum database{MYSQL, MONGODB};
	
	private static String host = "localhost";
//	private static database db = database.MYSQL;
	private static database db = database.MONGODB;
	
	public static String getHost()
	{
		return host;
	}
	
	public static database getDatabase()
	{
		return db;
	}
	
}
