package Model;

// v1.0.1

import java.util.ArrayList;
import DAO.*;

public class AdminFunctions extends UserFunctions 
{
	private IProduktkategorieDAO produktkategorieDAO;
	private IUserDAO userDAO;
	private Admin user;
	private IProduktDAO produktDAO;
	
	public AdminFunctions(Admin user) 
	{
		super(user);
		this.user=user;
	}

	public void aendereProduktgruppe() 
	{
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> showUserList()
	{
		ArrayList<User> userList = getUserDAO().getAllUsers();
		return userList;
	}
	
	public ArrayList<Integer> getUserTypeList (ArrayList<User> userList)
	{
		ArrayList<Integer> userType = new ArrayList<Integer>();
		
		for ( int i=0; i< userList.size(); i++) 
		{
			if (userList.get(i) instanceof Admin) userType.add(1);
			else if (userList.get(i) instanceof Kunde) userType.add(3);
		} 
		return userType;
	}

	public ArrayList<Produktkategorie> showKategorieList() 
	{
		ArrayList<Produktkategorie> kategorieList = getProduktkategorieDAO().getAllProduktkategorie();
		return kategorieList;
	}

	public void erstelleProduktgruppe(String name)
	{
		getProduktkategorieDAO().createProduktkategorie(name, "");
	}
	
	public void erstelleProdukt(int id, String bezeichnung, String beschreibung){
		getProduktDAO().createProdukt(id , bezeichnung, beschreibung, 0, 0, null);
	}

	public String accountAendern(String email, String pw_old, String pw_new, String pw_new2)
	{
		String error = "";
		if (!email.equals(user.getEmail()))
		{
			user.setEmail(email);
			error = error + " ";
		}
		error = error + changePassword(pw_old, pw_new, pw_new2);
		getUserDAO().updateAdmin(user);
		return error;
	}
	
	public void sperreAccount()
	{
		throw new UnsupportedOperationException();
	}
}