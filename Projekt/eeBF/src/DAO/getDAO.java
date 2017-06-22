package DAO;

import conf.Conf;

public class getDAO {
	
	public static IBestellpositionDAO getBestellpositionDAO()
	{
		switch( Conf.getDatabase() )
		{
			case MYSQL :
				return new MysqlBestellpositionDAO();
			case MONGODB :
//				return new MongodbBestellpositionDAO();	//TODO
				return null;
			default : 
				return null;
		}
	}
	
	public static IBestellungDAO getBestellungDAO()
	{
		switch( Conf.getDatabase() )
		{
			case MYSQL :
				return new MysqlBestellungDAO();
			case MONGODB :
//				return new MongodbBestellungDAO();	//TODO
				return null;
			default : 
				return null;
		}
	}
	
	
	public static IProduktDAO getProduktDAO()
	{
		switch( Conf.getDatabase() )
		{
			case MYSQL :
				return new MysqlProduktDAO();
			case MONGODB :
//				return new MongodbProduktDAO();;	//TODO
				return null;
			default : 
				return null;
		}
	}
	
	public static IProduktkategorieDAO getProduktkategorieDAO()
	{
		switch( Conf.getDatabase() )
		{
			case MYSQL :
				return new MysqlProduktkategorieDAO();
			case MONGODB :
//				return new MongodbProduktkategorieDAO();;	//TODO
				return null;
			default : 
				return null;
		}
	}
	
	public static IUserDAO getUserDAO()
	{
		switch( Conf.getDatabase() )
		{
			case MYSQL :
				return new MysqlUserDAO();
			case MONGODB :
//				return new MongodbUserDAO();;	//TODO
				return null;
			default : 
				return null;
		}
	}
}
