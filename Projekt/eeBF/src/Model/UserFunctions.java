package Model;

import DAO.*;

public abstract class UserFunctions 
{
	private User user;
	private IProduktkategorieDAO produktkategorieDAO;
	private IUserDAO userDAO;
	private IProduktDAO produktDAO;
	
	public UserFunctions(User user)
	{
		this.user = user;
		this.produktkategorieDAO=new MysqlProduktkategorieDAO();
		this.produktDAO = new MysqlProduktDAO();
		this.userDAO=new MysqlUserDAO();
	}
	
	public String changePassword(String pw_old, String pw_new, String pw_new2) 
	{
		String error = "";
		if (!pw_old.isEmpty())
		{
			System.out.println("1");
			if (pw_old.equals(user.getPasswort()) && pw_new.equals(pw_new2))
			{
				if(!pw_new.isEmpty())
				{
					System.out.println("2");
					user.setPasswort(pw_new);
				}
				else
				{
					System.out.println("3");
					error = error + "Das neue Passwort darf nicht leer sein!";
				}
			}
			else
			{
				System.out.println("4");
				error = error + "Das alte Passwort oder die Passwortwiederholung ist falsch!";
			}
		}
		else
		{
			if(pw_new.isEmpty() && pw_new2.isEmpty())
			{
				System.out.println("5");
				error = " ";
			}
			else
			{
				System.out.println("6");
				error = error + "Das alte Passwort muss angegeben werden!";
			}
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
	
	public IProduktDAO getProduktDAO(){
		return produktDAO;
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