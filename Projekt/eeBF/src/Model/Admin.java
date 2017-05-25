// TODO There is no reason for this class to exist

package Model;

import java.util.Calendar;

public class Admin extends User 
{
	public Admin(int id, String email, String passwort, Calendar createdate) 
	{
		super(id, email, passwort, createdate);
	}
}