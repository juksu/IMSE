package DAO;

import java.util.ArrayList;
import Model.*;

public interface IUserDAO
{
	public User loadUser(int id);
	public User loginUser(String email, String password);
	public void saveKunde(Kunde user);
	public void updateKunde(Kunde user);
	public void saveAdmin(Admin user);
	public void updateAdmin(Admin user);
	void setUserInvalid(User user, boolean invalid);
	public void createNewUser(String email, String passwort, String vorname, String type, String nachname, String land, int plz, String ort, String strasse, int hausnummer); 
	public ArrayList<Integer> userStatistics();
	public ArrayList<User> getAllUsers();
	
	public int getUserIDByEmail(String email);
	public String getUserEmail(int id);
	public String getUserPasswort(int id);
	public String getUserVorname(int id);
	public String getUserNachname(int id);
	public String getUserTyp(int id);
	public String getUserLand(int id);
	public int getUserPLZ(int id);
	public String getUserOrt(int id);
	public String getUserStrasse(int id);
	public int getUserHausnummer(int id);
	
	public void setUserEmail(int id, String email);
	public void setUserPasswort(User user);
	public void setUserTyp(int id, String type);
	public void setUserVorname(int id, String vorname);
	public void setUserNachname(int id, String nachname);
	public void setUserLand(int id, String land);
	public void setUserPLZ(int id, int plz);
	public void setUserOrt(int id, String ort);
	public void setUserStrasse(int id, String strasse);
	public void setUserHausnummer(int id, int hausnummer);
}