package Controller;

// v1.0.1

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

public class MenuServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user")==null)
		{  
			System.out.println("MenuServlet.doGet: nicht eingeloggt, weiter auf login.jsp");
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{ 
			User user = (User) session.getAttribute("user");
			if (user instanceof Admin) response.sendRedirect("admin");
			if (user instanceof Kunde) 
			{
				session.setAttribute("prev", "showAuktion");
				session.setAttribute("prevDat", "ViewAll");
				showAlleAuktionen(request, response); 
				response.setContentType("text/html");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("MenuServlet.doPost");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			response.sendRedirect("login");
			System.out.println("MenuServlet.doPost user nicht eingeloggt");
		}
		else if (session.getAttribute("user")!=null){
			User user = (User) session.getAttribute("user");
			if (user instanceof Admin) response.sendRedirect("admin");
			if (user instanceof Kunde)
			{
				KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
				if (request.getParameter("button") != null) 
				{ 
					String button = request.getParameter("button");
					switch (button) 
					{
						case "changeAccount":
						response.sendRedirect("accountEdit");
						break;
						
						case "logout":
						request.getRequestDispatcher("login").include(request, response);
						response.setContentType("text/html");
						break;
					}
				}
			}
		}	
	}
	
	public void showAlleAuktionen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
		String katId = kategorieSuche(request);
		forwardList(request, response);	
		System.out.println(request.getParameter("Kategorie"));
	} 
		
	public void forwardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
		request.setAttribute("userID", user.getId());
		request.getRequestDispatcher("UserContent.jsp").include(request, response);
	}
		
	public String kategorieSuche (HttpServletRequest request) throws ServletException, IOException 
	{
		String katId = null;
		if (request.getParameter("Kategorie")!= null) 
		{
			katId = request.getParameter("Kategorie");
		} 
		else 
		{ 
			katId="All";
		}
		System.out.println("MenuServlet.kategorieSuche: KatId: " +katId);
		return katId;
	}
}
