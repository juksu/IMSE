package Controller;

// v1.0

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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String pwcheck = request.getParameter("pwcheck");
		String nachname = request.getParameter("nachname");
		String vorname = request.getParameter("vorname");
		String strasse = request.getParameter("strasse");
		int plz = Integer.parseInt(request.getParameter("plz"));
		String ort = request.getParameter("ort");
		int hausnummer = Integer.parseInt(request.getParameter("hausnummer"));
		String land = request.getParameter("land");
		if (!email.isEmpty() && 
				!password.isEmpty() && 
				!pwcheck.isEmpty() && 
				!nachname.isEmpty() && 
				!vorname.isEmpty() &&
				!land.isEmpty() &&
				plz != 0 && 
				!ort.isEmpty() && 
				!strasse.isEmpty() && 
				hausnummer != 0 
			) {
			if (password.equals(pwcheck)) {
				System.out.println("pwcheck passed");
				IUserDAO userDAO = new MysqlUserDAO();
				Kunde user = new Kunde(0,email, password, nachname, vorname, land, plz, ort, strasse, hausnummer, null);
				System.out.println(user);
				userDAO.saveKunde(user);
				response.sendRedirect("login");
			} else {
				//TODO pwcheck failed, ausgabe an user
				System.out.println("pwcheck failed");
			}
		} else {
			//TODO ausgabe an user, felder dürfen nicht leer sein
			System.out.println("felder dürfen nicht leer sein");
		}


		//TODO password check failed, benachrichtigung
	}
	
	
}
