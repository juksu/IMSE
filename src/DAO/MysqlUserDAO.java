package DAO;

// v1.1.1

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import Model.*;

public class MysqlUserDAO implements IUserDAO
{
	private Connection conn = null;
	
	private Connection openConnection() throws SQLException, ClassNotFoundException
	{
    	return DBConnection.getMySQLConnection( DBConnection.userTypes.ADMIN );
	}
	
	public User loadUser(int id) 
	{
		User user = null;
			try {
				conn = openConnection();
		    	
				PreparedStatement ps =conn.prepareStatement("select * from benutzerkonto where aid=?");
				ps.setInt(1, id);
				ResultSet rs =ps.executeQuery();
				if (rs.next()) 
				{
					int loadId = id;
					String loadEmail = rs.getString("email");
					String loadPassword = rs.getString("Passwort");
					Calendar createdate = Calendar.getInstance();
					createdate.setTimeInMillis(rs.getTimestamp("createdate").getTime());
					String usertype = rs.getString("usertype");
					System.out.println("MysqlUserDAO.loadUser: userType: "+usertype);
					if(usertype.equals("admin"))
					{
						user = new Admin(loadId, loadEmail, loadPassword, createdate);
						System.out.println("MysqlUserDAO.loadUser: Admin-Objekt mit der id " +loadId+ " erstellt.");
					} 
					else if(usertype.equals("kunde"))
					{
						PreparedStatement psC =conn.prepareStatement("select * from kunde where aid=?");
						psC.setInt(1, id);
						ResultSet rsC =psC.executeQuery();
						if (rsC.next()) 
						{
							int loadIdC = id;
							String nachname = rsC.getString("Nachname");
							String vorname = rsC.getString("Vorname");
							String land =rsC.getString("Land");
							int plz =rsC.getInt("PLZ");
							String ort = rsC.getString("Ort");
							String strasse = rsC.getString("Strasse");
							int hausnummer =rsC.getInt("HausNr");
							user = new Kunde(loadId, loadEmail, loadPassword, nachname, vorname, land, plz, ort, strasse, hausnummer, createdate);
							System.out.println("MysqlUserDAO.loadUser: Kunde-Objekt mit der id " +loadIdC+ " erstellt.");
							rsC.close();
							psC.close();
						}
					} 
					else 
					{
						throw new IllegalArgumentException("User mit der ID: " +id+ 
							"wurde entweder nicht gefunden oder das Passwort ist falsch");
					} 
					return user;
	          }
				rs.close();
				ps.close();
	} 
			catch (SQLException | ClassNotFoundException ex)
			{
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
	  	return null;
	}
	
	public User loginUser(String email, String password)
	{
		User user = null;
		try 
		{
			conn = openConnection();
			PreparedStatement ps =conn.prepareStatement("select * from benutzerkonto where email=? and Passwort=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs =ps.executeQuery();
			if (rs.next()) 
			{
				int loadId = rs.getInt("aid");
				System.out.println("UserDAO.loginUser: userID: " +loadId);
				user =loadUser(loadId);
				rs.close();
				ps.close();
				return user;
			}
			else 
			{
				rs.close();
				ps.close();
				throw new IllegalArgumentException ("User konnte nicht eingeloggt werden. E-Mail oder Passwort falsch");
			}
		} 
		catch (SQLException | ClassNotFoundException e)
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

	
	private ResultSet getUserTable(int id) 
	{
		ResultSet rs= null;
        try 
        {
		conn = openConnection();
    	
       	 PreparedStatement ps =conn.prepareStatement("select * from benutzerkonto where aid=?");
            ps.setInt(1, id);
            rs =ps.executeQuery();
            if (rs.next()) 
            {
            	return rs;
            } else throw new IllegalArgumentException ("User mit der ID: "+id+" nicht gefunden");
            
        } 
        catch (SQLException | ClassNotFoundException ex)
        {
        	System.out.println(id);
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
		return rs;
	}
	
	public void saveKunde(Kunde user)
	{
		try 
		{
			System.out.println("MysqlUserDAO.saveUser(Kunde)");
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("insert into benutzerkonto "
				+ "(email, Passwort, usertype) "
				+ "VALUES (?, ?, ?)");
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setString(3, "kunde");
				ps.execute();
			PreparedStatement psC =conn.prepareStatement
			("insert into kunde "
				+ "(Nachname, Vorname, Land, PLZ, Strasse, Ort, HausNr, aid) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				psC.setString(1, user.getNachname());
				psC.setString(2, user.getVorname());
				psC.setString(3, user.getLand());
				psC.setInt(4, user.getPlz());		
				psC.setString(5, user.getOrt());			
				psC.setString(6, user.getStrasse());
				psC.setInt(7, user.getHausnummer());
				psC.setInt(8, getUserIDByEmail(user.getEmail()));
				psC.execute();
				psC.close();
			ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MYSQLUser, New User Creation failed");
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
	
	
	public void saveAdmin(Admin user)
	{
	try 
		{
			System.out.println("MysqlUserDAO.saveUser(Admin)");
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("insert into benutzerkonto "
				+ "(email, Passwort, usertype) "
				+ "VALUES (?, ?, ?)");
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setString(3, "admin");
				ps.execute();
				ps.close();
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MYSQLUser, New User Creation failed");
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

	public void updateKunde(Kunde user)
	{
		try
		{
			System.out.println("MysqlUserDAO.updateKunde(Kunde)");
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("update benutzerkonto set email=?,"
				+ " Passwort=? where aid=?");
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setInt(3, user.getId());	
				ps.execute();
				ps.close();	
			PreparedStatement psC = conn.prepareStatement
			("update kunde set Nachname=?, Vorname=?, Land=?, PLZ=?, Ort=?"
				+ ", Strasse=?, HausNr=? where aid=?");
				psC.setString(1, user.getNachname());
				psC.setString(2, user.getVorname());
				psC.setString(3, user.getLand());
				psC.setInt(4, user.getPlz());
				psC.setString(5, user.getOrt());
				psC.setString(6, user.getStrasse());
				psC.setInt(7, user.getHausnummer());
				psC.setInt(8, user.getId());
				psC.execute();
				psC.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MysqlUserDAO, New User Updated failed");
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

	public void updateAdmin(Admin user)
	{
		try
		{
			System.out.println("MysqlUserDAO.updateAdmin(Admin)");
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
			("update benutzerkonto set email=?,"
				+ " Passwort=? where aid=?");
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setInt(3, user.getId());	
				ps.execute();
				ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MysqlUserDAO, New User Updated failed");
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

	public void setUserInvalid(User user, boolean invalid)
	{
		try {
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement("update benutzerkonto set isInvalid=? where aid=?");
			ps.setBoolean(1, invalid);
			ps.setInt(2, user.getId());
			ps.executeUpdate();
			ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e)
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
	}
	
	public void createNewUser(String email, String passwort, String type, String vorname, String nachname, 
			String land, int plz, String ort, String strasse, int hausnummer) {
		try {
			conn = openConnection();
	    	PreparedStatement ps = conn.prepareStatement
	    	("insert into benutzerkonto "
	    		+ "(email, Passwort, usertype) "
	    		+ "VALUES (?, ?, ?)");
	    		ps.setString(1, email);
	    		ps.setString(2, passwort);
	    		ps.setString(3, type);
	    		ps.execute();
	    		ps.close();	
//			conn = openConnection();
			PreparedStatement psC =conn.prepareStatement
			("insert into kunde "
				+ "(Nachname, Vorname, Land, PLZ, Ort, Strasse, HausNr) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
				psC.setString(1, nachname);
				psC.setString(2, vorname);
				psC.setString(3, land);
				psC.setInt(4, plz);
				psC.setString(5, ort);
				psC.setString(6, strasse);
				psC.setInt(7, hausnummer);
				psC.execute();
				psC.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MYSQLUser, New User Creation failed");
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> userStatistics()
	{
		ArrayList<Integer> userCount = new ArrayList<Integer>();
		int user = 0;
		int kunde= 0;
		int admin = 0;
		try 
		{
			System.out.println("Mysqluserdao userstat");
			conn = openConnection();
			PreparedStatement psadmin = conn.prepareStatement("select * from benutzerkonto where usertype='admin' and isValid='true'");
			ResultSet rsadmin;
			rsadmin = psadmin.executeQuery();
			System.out.println(rsadmin);
			while (rsadmin.next())
			{
				System.out.println("admin");
				++admin;
			}
			rsadmin.close();
			psadmin.close();
	
			PreparedStatement pskunde = conn.prepareStatement("select * from benutzerkonto where usertype='kunde' and isValid='true'");
			ResultSet rskunde;
			rskunde = pskunde.executeQuery();
			while (rskunde.next())
			{
				++kunde;
			}
			rskunde.close();
			pskunde.close();
			
			user = admin+kunde;
			userCount.add(user);
			userCount.add(kunde);
			userCount.add(admin);

		} 
		catch (SQLException | ClassNotFoundException e) 
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
		return userCount;
	}
	
	public ArrayList<User> getAllUsers()
	{
		User user;
		ArrayList<User>userList = new ArrayList<User>();
		try 
		{
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement
					("select * from benutzerkonto");
			ResultSet rs =ps.executeQuery();
			while (rs.next()) 
			{
				int id = rs.getInt("aid");
				String email = rs.getString("email");
				String password = "0";
				Calendar createdate = Calendar.getInstance();
				createdate.setTimeInMillis(rs.getTimestamp("createdate").getTime());
				String usertype = rs.getString("usertype");
				if(usertype.equals("admin")) {
					user = new Admin(id, email, password, createdate);
					userList.add(user);
				} 
				else if(usertype.equals("kunde"))
				{
					user = new Kunde(id, email, password, null, null, null, 0, null, null, 0, null);
					userList.add(user);
				}
			}
			ps.close();
			rs.close();
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MYSQLUser, New User Creation failed");
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
		return userList;
	}

	public int getUserIDByEmail(String email)
	{
		int userID=-1;
		if (conn != null)
		{
            try 
            {
           	 PreparedStatement ps =conn.prepareStatement("select * from benutzerkonto where email=?");
                ps.setString(1, email);
                ResultSet rs =ps.executeQuery();
                if (rs.next())
                {
                	userID = rs.getInt("aid");
                }
            } catch (SQLException ex)
            {
            	System.out.println(email);
                ex.printStackTrace();
                userID = -1;
            } 
        }
		return userID;
	}

	public String getUserEmail(int id)
	{
		String email;
		if (conn != null) {
			try
			{
	            ResultSet rs= getUserTable(id);
	            email = rs.getString("email");
	            return email;
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

	public String getUserPasswort(int id)
	{
		String passwort;
		if (conn != null) 
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            passwort = rs.getString("Passwort");
	            return passwort;
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

	public String getUserVorname(int id)
	{
		String vorname;
		if (conn != null)
		{
			try
			{
	            ResultSet rs= getUserTable(id);
	            vorname = rs.getString("Vorname");
	            return vorname;
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

	public String getUserNachname(int id)
	{
		String nachname;
		if (conn != null) 
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            nachname = rs.getString("Nachname");
	            return nachname;
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

	public String getUserTyp(int id)
	{
		String type;
		if (conn != null)
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            type = rs.getString("usertype");
	            return type;
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

	public String getUserCreateDate(int id)
	{
		String type;
		if (conn != null)
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            type = rs.getString("createdate");
	            return type;
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
	
	public String getUserStrasse(int id)
	{
		String strasse;
		if (conn != null)
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            strasse = rs.getString("Strasse");
	            return strasse;
			} catch (IllegalArgumentException e)
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

	public int getUserPLZ(int id)
	{
		int plz;
		if (conn != null)
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            plz = rs.getInt("PLZ");
	            return plz;
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			} catch (SQLException e) 
			{
				e.printStackTrace();				
			}
		}
		return 0;
	}

	public String getUserOrt(int id)
	{
		String ort;
		if (conn != null) 
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            ort = rs.getString("Ort");
	            return ort;
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

	public int getUserHausnummer(int id)
	{
		int hausnummer;
		if (conn != null)
		{
			try {
	            ResultSet rs= getUserTable(id);
	            hausnummer = rs.getInt("HausNr");
	            return hausnummer;
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
		return 0;
	}

	public String getUserLand(int id)
	{
		String land;
		if (conn != null) 
		{
			try 
			{
	            ResultSet rs= getUserTable(id);
	            land = rs.getString("land");
	            return land;
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

	public void setUserEmail(int id, String email)
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement ps = conn.prepareStatement("update benutzerkonto set email=?"+" where aid=?");
				ps.setString(1, email);
				ps.setInt(2, id);
				ps.executeUpdate();
				System.out.println(ps);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserPasswort(User user) 
	{
		try
		{
			System.out.println("MysqlUserDAO.saveUser(Kunde)");
			conn = openConnection();
			PreparedStatement ps = conn.prepareStatement("update benutzerkonto set Passwort=?"+" where aid=?");
			ps.setString(1, user.getPasswort());
			ps.setInt(2, user.getId());
			ps.executeUpdate();
			ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MYSQLUser, New User Creation failed");
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

	public void setUserVorname(int id, String vorname) 
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update kunde set Vorname=?"+" where aid=?");
				psC.setString(1, vorname);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}		
	}

	public void setUserNachname(int id, String nachname) 
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update kunde set Nachname=?"+" where aid=?");
				psC.setString(1, nachname);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserTyp(int id, String type) 
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement ps = conn.prepareStatement("update benuzterkonto set usertype=?"+" where aid=?");
				ps.setString(1, type);
				ps.setInt(2, id);
				ps.executeUpdate();
				System.out.println(ps);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserStrasse(int id, String strasse) 
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update Kunde set Strasse=?"+" where aid=?");
				psC.setString(1, strasse);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserPLZ(int id, int plz) 
	{
		if (conn != null)
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update kunde set PLZ=?"+" where aid=?");
				psC.setInt(1, plz);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserOrt(int id, String ort)
	{
		if (conn != null) 
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update kunde set Ort=?"+" where aid=?");
				psC.setString(1, ort);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserHausnummer(int id, int hausnummer)
	{
		if (conn != null) {
			try {
				PreparedStatement psC = conn.prepareStatement("update kunde set HausNr=?"+" where aid=?");
				psC.setInt(1, hausnummer);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}

	public void setUserLand(int id, String land) {
		if (conn != null) 
		{
			try 
			{
				PreparedStatement psC = conn.prepareStatement("update kunde set Land=?"+" where aid=?");
				psC.setString(1, land);
				psC.setInt(2, id);
				psC.executeUpdate();
				System.out.println(psC);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}	
	}
}
