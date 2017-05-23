package Model;

import java.util.Calendar;

public abstract class User
{
	int id;
	private String email;
	private String passwort; //Encryption!
	Calendar createdate;
	boolean isInvalid;

	public User(int id, String email, String passwort, Calendar createdate) 
	{
		setId(id);
		setEmail(email);
		setPasswort(passwort);
	}

	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPasswort() 
	{
		return passwort;
	}
	
	public void setPasswort(String passwort)
	{
		this.passwort = passwort;
	}
	
	public Calendar getCreatedate()
	{
		return createdate;
	}
	
	public void setCreatedate(Calendar createdate)
	{
		if (createdate != null)
		{
			this.createdate = createdate;
		}
	}
	
	public boolean isInvalid()
	{
		return isInvalid;
	}

	public void setInvalid(boolean isInvalid)
	{
		this.isInvalid = isInvalid;
	}
}