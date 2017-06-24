package Controller;

import javax.servlet.http.HttpSession;

public class Utils
{
	/**
	 * checks if the user is logged in
	 * @param request
	 * @return
	 */
	public static boolean userLogedInCheck( HttpSession session )
	{	
		if( session == null )
			return false;
		if( session.getAttribute( "user" ) == null )
			return false;
		return true;
	}
};
