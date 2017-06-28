package Model;

// v1.0.1

import java.util.ArrayList;
import DAO.*;

public class KundenFunctions extends UserFunctions 
{
	private IProduktkategorieDAO ProduktkategorieDAO;
	private IUserDAO userDAO;
	private Kunde user;

	public KundenFunctions(Kunde user)
	{
		super(user);
		this.user = user;
	}

	public String accountAendern(String email, String pw_old, String pw_new, String pw_new2, String nachname, String vorname,
								String land, int plz,  String ort,String strasse, int hausnummer)
	{
		String error = "";
		if (!email.equals(user.getEmail()))
		{
			user.setEmail(email);
			error = error + " ";
		}
		error = error + changePassword(pw_old, pw_new, pw_new2);
		user.setNachname(nachname);
		user.setVorname(vorname);
		user.setLand(land);
		user.setPlz(plz);
		user.setOrt(ort);
		user.setStrasse(strasse);
		user.setHausnummer(hausnummer);

		getUserDAO().updateKunde(user);
		return error;
	}
	
	public ArrayList<Produktkategorie> showKategorieList()
	{
		ArrayList<Produktkategorie> kategorieList = getProduktkategorieDAO().getAllProduktkategorie();
		return kategorieList;
	}
}