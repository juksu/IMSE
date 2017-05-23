package DAO;

import java.util.ArrayList;
import Model.*;

public interface UserDAO
{
	public User loadUser(int id);
	public User loginUser(String email, String password);
	public void saveKunde(Kunde user);
	public void updateKunde(Kunde user);
	public void saveAdmin(Admin user);
	public void updateAdmin(Admin user);
	void setUserInvalid(User user, boolean invalid);
	public void createNewUser(String email, String passwort, String vorname, String nachname, String type,	String strasse, int plz, String ort, String hausnummer, String iban, String land); 
	public ArrayList<Integer> userStatistics();
	public ArrayList<User> getAllUsers();
	
	public int getUserIDByEmail(String email);
	public String getUserEmail(int id);
	public String getUserPasswort(int id);
	public String getUserVorname(int id);
	public String getUserNachname(int id);
	public String getUserTyp(int id);
	public String getUserStrasse(int id);
	public int getUserPLZ(int id);
	public String getUserOrt(int id);
	public String getUserHausnummer(int id);
	public String getUserLand(int id);
	
	public void setUserEmail(int id, String email);
	public void setUserPasswort(User user);
	public void setUserVorname(int id, String vorname);
	public void setUserNachname(int id, String nachname);
	public void setUserTyp(int id, String type);
	public void setUserStrasse(int id, String strasse);
	public void setUserPLZ(int id, int plz);
	public void setUserOrt(int id, String ort);
	public void setUserHausnummer(int id, String hausnummer);
	public void setUserLand(int id, String land);
}
