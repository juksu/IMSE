package Model;

import java.util.ArrayList;
import DAO.*;

public class AdminFunctions extends UserFunctions 
{
	private produktkategorieDAO produktkategorieDAO;
	private UserDAO userDAO;
	private Admin user;
	
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
	
	public void erstelleUser() 
	{
		throw new UnsupportedOperationException();
	}

	public void erstelleProduktgruppe(String name)
	{
		getProduktkategorieDAO().createProduktkategorie(name, "");
	}

	public void sperreAccount()
	{
		throw new UnsupportedOperationException();
	}
}