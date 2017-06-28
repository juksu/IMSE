package Controller;

// v1.0.2

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.*;
import Model.*;

public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("Register.jsp").include(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		int plz = 0;
		int hausnummer = 0;
		String error = "";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String pwcheck = request.getParameter("pwcheck");
		String nachname = request.getParameter("nachname");
		String vorname = request.getParameter("vorname");
		String strasse = request.getParameter("strasse");
		String ort = request.getParameter("ort");
		String land = request.getParameter("land");
		try 
		{
			plz = Integer.parseInt(request.getParameter("plz"));
			hausnummer = Integer.parseInt(request.getParameter("hausnummer"));
		} 
		catch (NumberFormatException e) 
		{
			 error = error + "Hinweis: PLZ und HausNr sind als Zahl einzugeben. ";
		}
		if (!email.isEmpty() && 
				!password.isEmpty() && 
				!pwcheck.isEmpty() && 
				!nachname.isEmpty() && 
				!vorname.isEmpty() &&
				!land.isEmpty() &&
				plz != 0 && 
				!ort.isEmpty() && 
				!strasse.isEmpty() && 
				hausnummer != 0)
			{
			if (password.equals(pwcheck)) 
			{
				System.out.println("pwcheck passed");
				//IUserDAO userDAO = new MysqlUserDAO();
				IUserDAO userDAO = new MongodbUserDAO();
				Kunde user = new Kunde(0,email, password, nachname, vorname, land, plz, ort, strasse, hausnummer, null);
				System.out.println(user);
				userDAO.saveKunde(user);
			} 
			else
			{
				System.out.println("pwcheck failed");
				error = error + "Wiederholtes Passwort nicht ident!";
			}
		}
		else 
		{
			System.out.println("Felder dürfen nicht leer sein");
			error = error + "Eingabe fehlt!";
		}
		if (!error.isEmpty())
		{
			request.setAttribute("error", error);
			request.getRequestDispatcher("Register.jsp").include(request, response);
		}
		else 
		{
			response.sendRedirect("login");
		}
	}
}
