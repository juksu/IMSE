package Model;

import DAO.*;

public abstract class UserFunctions 
{
	private User user;
	private IProduktkategorieDAO produktkategorieDAO;
	private IUserDAO userDAO;
	
	public UserFunctions(User user)
	{
		this.user = user;
		this.produktkategorieDAO=new MysqlProduktkategorieDAO();
		this.userDAO=new MysqlUserDAO();
	}
	
	public String changePassword(String pw_old, String pw_new, String pw_new2) 
	{
		String error="";
		if (!(pw_old==null) &&pw_old.equals(user.getPasswort())&& pw_new.equals(pw_new2))
		{
			user.setPasswort(pw_new);
		} 
		else 
		{
			error = "Das alte Passwort oder die Passwortwiederholung ist falsch";
		}
		return error;
	}

	public void accountDeaktivieren() 
	{
		getUserDAO().setUserInvalid(user, true);
		
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	
	public IProduktkategorieDAO getProduktkategorieDAO() 
	{
		return produktkategorieDAO;
	}

	public void setProduktkategorieDAO(IProduktkategorieDAO produktkategorieDAO)
	{
		this.produktkategorieDAO = produktkategorieDAO;
	}

	public IUserDAO getUserDAO()
	{
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
}