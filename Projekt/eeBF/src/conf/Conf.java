package conf;

public class Conf {
	// available hosts: ["localhost", "www.eebf.at"]
	// available databases: ["mysql", "mongodb"]
	public enum database{MYSQL, MONGODB};
	
	private static String host = "localhost";
	private static database db = database.MYSQL;
	
	public static String getHost()
	{
		return host;
	}
	
	public static database getDatabase()
	{
		return db;
	}
	
}
