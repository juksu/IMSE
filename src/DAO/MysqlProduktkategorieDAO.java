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
	private Connection conn = null;

	private Connection openConnection() throws SQLException, ClassNotFoundException
	{
    	return DBConnection.getMySQLConnection( DBConnection.userTypes.CUSTOMER );
	}
	
	private Produktkategorie createProduktKatObject(ResultSet rs) throws SQLException
	{
		int id = rs.getInt("kid");
		String titel=rs.getString("bezeichnung");
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
			// TODO oberkategorie
			PreparedStatement ps = conn.prepareStatement
			("insert into produktkategorie (bezeichnung, beschreibung) VALUES(?, ?)");
				ps.setString(1, name);
				ps.setString(2, beschreibung);
				ps.execute();
				ps.close();
		} 
		catch (SQLException e)
		{
			System.out.println("MYSQLAuktion, New produktkategorie Creation failed");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
	
	//Lena
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
}
