package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

public class MysqlProduktkategorieDAO implements IProduktkategorieDAO 
{
	private String dbPath;
	private String dbUser;
	private String dbPassword;
	private Connection conn = null;

	public MysqlProduktkategorieDAO () 
	{
		setDbPath("jdbc:mysql://localhost:3306/eebf?useSSL=false");
		setDbUser("eeBF_Admin");
		setDbPassword("Tombstone");
      	try
      	{
      		Class.forName("com.mysql.jdbc.Driver");
        } 
      	catch (ClassNotFoundException e)
      	{
      		System.out.println("An error occurred. com.mysql.jdbc.Driver konnte nicht geladen werden");
      		e.printStackTrace();
      	} 	
	}
	
	private Connection openConnection() throws SQLException
	{
		conn = null;
    	conn = DriverManager.getConnection(getDbPath(), getDbUser(), getDbPassword());
    	return conn;
	}
	
	private Produktkategorie createProduktKatObject(ResultSet rs) throws SQLException
	{
		int id = rs.getInt("id");
		String titel=rs.getString("name");
		String beschreibung = rs.getString("beschreibung");
		// TODO Oberkategorie für jetzt null (jede Kategorie ist die Wurzel)
		Produktkategorie pK = new Produktkategorie (id, titel, beschreibung, null);
		return pK;
	}	

	public void createProduktkategorie(String name, String beschreibung)
	{
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("insert into produktkategorie (name, beschreibung) VALUES(?, ?)");
				ps.setString(1, name);
				ps.setString(2, beschreibung);
				ps.execute();
				ps.close();
		} 
		catch (SQLException e)
		{
			System.out.println("MYSQLAuktion, New produktkategorie Creation failed");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}	

	public int getAnzahl() {
		int i =0;
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produktkategorie");
			ResultSet rs=ps.executeQuery();
			if (rs.next()) i++;
			rs.close();
			ps.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			try
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return i;	
	}
	
	public Produktkategorie getProduktkategorie()
	{
		return null;
		
	}
	
	public ArrayList<Produktkategorie> getAllProduktkategorie() 
	{
		ArrayList<Produktkategorie> ProduktList = new ArrayList<Produktkategorie>();
		try
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produktkategorie");
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				ProduktList.add(createProduktKatObject(rs));
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return ProduktList;
	}

	public int getIdByName(String name)
	{
		System.out.println("MYSQLProduktkategorieDAO.getIdByName: " + name);
		int id;
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produktkategorie where name=?" );
				ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			rs.next();
	        id = rs.getInt("id");
	        return id;
		} 
		catch (IllegalArgumentException e)
		{
				e.printStackTrace();
		} 
		catch (SQLException e)
		{
				e.printStackTrace();				
		}
		finally 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return  -1;
	}
	
	public String getName(int id) 
	{
		String name;
		if (conn != null) 
		{
			try 
			{
				PreparedStatement ps = conn.prepareStatement
						("select * from produktkategorie");
				ResultSet rs=ps.executeQuery();
	            name = rs.getString("name");
	            return name;
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();				
			}
		}
		return null;
	}

	public String getBeschreibung(int id) 
	{
		String beschreibung;
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produktkategorie");
			ResultSet rs=ps.executeQuery();
            beschreibung = rs.getString("beschreibung");
            rs.close();
            ps.close();
            return beschreibung;
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();				
		}
		finally 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getDbPath()
	{
		return dbPath;
	}

	public void setDbPath(String dbPath)
	{
		this.dbPath = dbPath;
	}

	public String getDbUser()
	{
		return dbUser;
	}

	public void setDbUser(String dbUser)
	{
		this.dbUser = dbUser;
	}

	public String getDbPassword()
	{
		return dbPassword;
	}

	public void setDbPassword(String dbPassword)
	{
		this.dbPassword = dbPassword;
	}
}
