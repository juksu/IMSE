package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

@WebServlet (name="UserAccountEditServlet",
			urlPatterns={"/accountEdit"})

public class UserAccountEditServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			System.out.println("UserAccountEdit.doGet: nicht eingeloggt, weiter auf login.jsp");
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{ // include content if attribute login is set
			User user = (User) session.getAttribute("user");
			if (user instanceof Admin) response.sendRedirect("admin");
			Kunde kunde = (Kunde) session.getAttribute("user");
			request.setAttribute("kunde", kunde);
			forwardList (request, response, user);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("UserAccountEdit.doPost");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null){  
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{
			User user = (User) session.getAttribute("user");
			if (user instanceof Admin) response.sendRedirect("admin");
			KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
			if (request.getParameter("button") != null) 
			{
				String button = request.getParameter("button");
				switch (button) 
				{
					case "change":
					changeData(request, response);
					break;
					
					case "lock":
					userFunctions.accountDeaktivieren();
					break;
				}
			}
		}
	}
	
	public void changeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//passwort aendern ja nein?
		HttpSession session = request.getSession(true);
		KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
		String error ="";
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("nachname"));
		String email = request.getParameter("email");
		String pw_old = request.getParameter("pw_old");
		String pw_new = request.getParameter("pw_new");
		String pw_new2 = request.getParameter("pw_new2");
		String nachname = request.getParameter("nachname");
		String vorname = request.getParameter("vorname");
		String land = request.getParameter("land");
		int plz = Integer.parseInt(request.getParameter("plz"));
		String ort = request.getParameter("ort");
		String strasse = request.getParameter("strasse");
		int hausnummer = Integer.parseInt(request.getParameter("hausnummer"));

		System.out.println(nachname);
		try 
		{
			Integer.parseInt(request.getParameter("plz"));
			System.out.println("Error: "+error);
		} 
		catch (NumberFormatException e) 
		{
			 error="Die eingegebene PLZ ist keine gueltige Nummer";
		}
		error = error + userFunctions.accountAendern(email, pw_old, pw_new, pw_new2, nachname, vorname, land, plz, ort, strasse, hausnummer);
		System.out.println(error);
		session.setAttribute("error", error);
		if (error.isEmpty())
		{
			request.getRequestDispatcher("UserContent.jsp").include(request, response);
		}
		else 
		{
			session.invalidate();
		}
		doGet(request,response);
	}
	
	public void forwardList(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
		//ArrayList<Produktkategorie> kategorieList = userFunctions.showKategorieList();
		//request.setAttribute("kategorieList", kategorieList);
		request.getRequestDispatcher("KundeAccountEdit.jsp").include(request, response);
		session.removeAttribute("error");
	}
}
