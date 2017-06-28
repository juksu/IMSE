package DAO;

// v0.9

import mongodb.jdbc.MongoPreparedStatement;
import unity.util.StringFunc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import Model.*;

public class MongodbUserDAO implements IUserDAO
{
	private Connection conn = null;
	
	private Connection openConnection() throws SQLException, ClassNotFoundException
	{
    	return DBConnection.getMongoDBConnection();
	}
	
	public User loadUser(int id) 
	{
		User user = null;

        	try        	
        	{
        		conn = openConnection();
        		String sql = "select * from benutzerkonto where aid=?;";
				MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs =ps.executeQuery();
				if (rs.next())  
				{
					int loadId = id;
					String loadEmail = rs.getString("email");
					String loadPassword = rs.getString("Passwort");
					Calendar createdate = Calendar.getInstance();
					createdate.setTime(rs.getDate("createdate"));
					String usertype = rs.getString("usertype");
					System.out.println("MongodbUserDAO.loadUser: userType: "+usertype);
					if(usertype.equals("admin"))
					{
						user = new Admin(loadId, loadEmail, loadPassword, createdate);
						System.out.println("MysqlUserDAO.loadUser: Admin-Objekt mit der id " +loadId+ " erstellt.");
					} 
					else if(usertype.equals("kunde"))
					{
						String sqlC = "select * from kunde where aid=?";
						MongoPreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sqlC);
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
							System.out.println("MongodbUserDAO.loadUser: Kunde-Objekt mit der id " +loadIdC+ " erstellt.");
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
			String sql = "select * from benutzerkonto where email = ? and Passwort = ?;";
			MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				int loadId = rs.getInt("aid");
				System.out.println("UserDAO.loginUser: userID: " +loadId);
				user = loadUser(loadId);
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
    	
       	 String sql = ("select * from benutzerkonto where aid=?");
       	 MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
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
			System.out.println("MongodbUserDAO.saveUser(Kunde)");
			conn = openConnection();
			int lastUserID = getLastUserID()+1; 
			String sql = "insert into benutzerkonto (aid, email, Passwort, createdate, usertype) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, lastUserID);
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPasswort());
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(5, "kunde");
				ps.execute();
					
			String sqlC = "insert into kunde (cid, Nachname, Vorname, Land, PLZ, Strasse, Ort, HausNr, aid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sqlC);
				psC.setInt(1, getLastCID()+1);
				psC.setString(2, user.getNachname());
				psC.setString(3, user.getVorname());
				psC.setString(4, user.getLand());
				psC.setInt(5, user.getPlz());		
				psC.setString(6, user.getOrt());			
				psC.setString(7, user.getStrasse());
				psC.setInt(8, user.getHausnummer());
				psC.setInt(9, lastUserID);
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
			System.out.println("MongodbUserDAO.saveUser(Admin)");
			conn = openConnection();
			String sql = "insert into benutzerkonto (aid, email, Passwort, createdate, usertype) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, getLastUserID()+1);
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPasswort());
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(5, "admin");
				ps.execute();
				ps.close();
		} 
		catch (SQLException | ClassNotFoundException e ) 
		{
			System.out.println("MongodbUserDAO, New User Creation failed");
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				System.out.println("MongodbUserDAO: ID" + getLastUserID()+1+","+LocalDateTime.now().toString() );
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
			System.out.println("MongodbUserDAO.updateKunde(Kunde)");
			conn = openConnection();
			String sql = "update benutzerkonto set email=?, Passwort=? where aid=?;";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setInt(3, user.getId());	
				ps.executeUpdate();
			
			String sqlC = "update kunde set Nachname=?, Vorname=?, Land=?, PLZ=?, Ort=?, Strasse=?, HausNr=? where aid=?;";	
			PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sqlC);	
				psC.setString(1, user.getNachname());
				psC.setString(2, user.getVorname());
				psC.setString(3, user.getLand());
				psC.setInt(4, user.getPlz());
				psC.setString(5, user.getOrt());
				psC.setString(6, user.getStrasse());
				psC.setInt(7, user.getHausnummer());
				psC.setInt(8, user.getId());
				psC.executeUpdate();
				psC.close();	
				
			ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MongodbUserDAO, New User Updated failed");
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
			System.out.println("MongodbUserDAO.updateAdmin(Admin)");
			conn = openConnection();
			String sql = "update benutzerkonto set email=?, Passwort=? where aid=?;";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPasswort());
				ps.setInt(3, user.getId());	
				ps.executeUpdate();
				ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MongodbUserDAO, New User Updated failed");
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
			String sql = "update benutzerkonto set isInvalid=? where aid=?;";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
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
			System.out.println("MongodbUserDAO.saveUser(Kunde)");
			conn = openConnection();
			String sql = "insert into benutzerkonto (aid, email, Passwort, createdate, usertype) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, getLastUserID()+1);
				ps.setString(2, email);
				ps.setString(3, passwort);
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(5, type);
				ps.execute();
					
			String sqlC = "insert into kunde (cid, Nachname, Vorname, Land, PLZ, Strasse, Ort, HausNr, aid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sqlC);
				psC.setInt(1, getLastCID()+1);
				psC.setString(2, nachname);
				psC.setString(3, vorname);
				psC.setString(4, land);
				psC.setInt(5, plz);		
				psC.setString(6, ort);			
				psC.setString(7, strasse);
				psC.setInt(8, hausnummer);
				psC.setInt(9, getLastUserID()+1);
				psC.execute();
				psC.close();
			ps.close();
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MongodbUserDAO, New User Creation failed");
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
			System.out.println("MongodbUserDAO userstat");
			conn = openConnection();
			String sqlA = "select * from benutzerkonto where usertype='admin' and isValid='true';";
			MongoPreparedStatement psadmin = (MongoPreparedStatement) conn.prepareStatement(sqlA);
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
			
			String sqlK = "select * from benutzerkonto where usertype='kunde' and isValid='true';";
			MongoPreparedStatement pskunde = (MongoPreparedStatement) conn.prepareStatement(sqlK);
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
			String sql = "select * from benutzerkonto;";
			MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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
			System.out.println("MongodbUserDAO, New User Creation failed");
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
            	String sql = "select * from benutzerkonto where email=?;";
            	MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
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

	
	public int getLastUserID()
	{
		int lastUserID = -1;
		if (conn != null)
		{
            try 
            {
            	String sql = "select count(*) as aidcount from benutzerkonto;";
            	MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
    			if (rs.next())
                {
                	lastUserID = rs.getInt("aidcount");
                }
            } catch (SQLException ex)
            {
            	System.out.println("lastUSerID: " + lastUserID);
                ex.printStackTrace();
                lastUserID = -1;
            } 
        }
		return lastUserID;
	}
	
	
	public int getLastCID()
	{
		int lastCID = -1;
		if (conn != null)
		{
            try 
            {
            	String sql = "select count(*) as cidcount from kunde;";
            	MongoPreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
    			if (rs.next())
                {
                	lastCID = rs.getInt("cidcount");
                }
            } catch (SQLException ex)
            {
            	System.out.println("lastUSerID: " + lastCID);
                ex.printStackTrace();
                lastCID = -1;
            } 
        }
		return lastCID;
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
				String sql = "update benutzerkonto set email=? where aid=?;";
				PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
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
			System.out.println("MongodbUserDAO.saveUser(Kunde)");
			conn = openConnection();
			String sql = "update benutzerkonto set Passwort=? where aid=?;";
			PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, user.getPasswort());
			ps.setInt(2, user.getId());
			ps.executeUpdate();
			ps.close();	
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			System.out.println("MongodbUserDAO, New User Creation failed");
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
				String sql = "update kunde set Vorname=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update kunde set Nachname=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update benuzterkonto set usertype=? where aid=?;";
				PreparedStatement ps = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update Kunde set Strasse=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update kunde set PLZ=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update kunde set Ort=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update kunde set HausNr=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
				String sql = "update kunde set Land=? where aid=?;";
				PreparedStatement psC = (MongoPreparedStatement) conn.prepareStatement(sql);
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
