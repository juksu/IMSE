package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

public class MysqlProduktDAO implements IProduktDAO 
{
	private String dbPath;
	private String dbUser;
	private String dbPassword;
	private Connection conn = null;

	public MysqlProduktDAO () 
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
	
	private Produkt createProduktObject(ResultSet rs) throws SQLException
	{
		int id = rs.getInt("id");
		String name=rs.getString("name");
		String beschreibung = rs.getString("beschreibung");
		float price = rs.getFloat("price");
		int quantity = rs.getInt("quantity");
		
		// TODO Oberkategorie für jetzt null (jede Kategorie ist die Wurzel)
		Produkt p = new Produkt (id, name, beschreibung,price, quantity, null);
		return p;
	}	

	public void createProdukt(int id, String name, String description, int preis, int quantity, int lagerid)
	{
		try 
		{
			conn = openConnection();
			// TODO oberkategorie
			PreparedStatement ps = conn.prepareStatement
			("insert into produkt(PBezeichnung, PBeschreibung, preis, menge, sid) VALUES(?, ?, ?, ?, ?)");
				
				//ps.setInt(1, id);
				ps.setString(1, name);
				ps.setString(2, description);
				ps.setInt(3, preis);
				ps.setInt(4, quantity);
				ps.setInt(5, lagerid);
				ps.execute();
				ps.close();
		} 
		catch (SQLException e)
		{
			System.out.println("MYSQLAuktion, New produkt Creation failed");
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
			("select * from produkt");
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
	
	public Produkt getProdukt()
	{
		return null;
		
	}
	
	public ArrayList<Produkt> getAllProdukten() 
	{
		ArrayList<Produkt> ProduktList = new ArrayList<Produkt>();
		try
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produkt");
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				ProduktList.add(createProduktObject(rs));
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
	
	public ArrayList<Produkt> getAllProduktenByLagerId(int sid) 
	{
		System.out.println("MYSQLProduktkDAO.getAllProduktenByLagerId");
		ArrayList<Produkt> ProduktList = new ArrayList<Produkt>();
		ResultSet rs= null;
        try 
        {
		conn = openConnection();
    	
       	 PreparedStatement ps =conn.prepareStatement("select * from produkt where sid=?");
            ps.setInt(1, sid);
            rs =ps.executeQuery();
            if (rs.next()) 
            {
            	ProduktList.add(createProduktObject(rs));
            } else throw new IllegalArgumentException ("Produkt mit  sID: "+sid+" nicht gefunden");
            
        } 
        catch (SQLException ex)
        {
        	System.out.println(sid);
            ex.printStackTrace();
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
/*
	public ArrayList<Produkt> getAllProduktenByLagerId(int sid) 
	{
		System.out.println("MYSQLProduktkDAO.getAllProduktenByLagerId");
		ArrayList<Produkt> ProduktList = new ArrayList<Produkt>();
		try
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produkt where sid=?");
			ps.setInt(1, sid);
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				ProduktList.add(createProduktObject(rs));
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
	*/
	
	/*
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
*/
	public String getBezeichnung(int id) 
	{
		String bezeichnung;
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("select * from produkt");
			ResultSet rs=ps.executeQuery();
            bezeichnung = rs.getString("PBezeichnung");
            rs.close();
            ps.close();
            return bezeichnung;
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