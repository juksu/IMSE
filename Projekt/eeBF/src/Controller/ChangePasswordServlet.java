package Controller;

// v1.0.1

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

@WebServlet (name="UserAccountEditServlet",
			urlPatterns={"/changePassword"})

public class ChangePasswordServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			System.out.println("MenuServlet.doGet: nicht eingeloggt, weiter auf login.jsp");
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{ 
			User user = (User) session.getAttribute("user");
			if (user instanceof Kunde) response.sendRedirect("accountEdit");
			Admin admin = (Admin) session.getAttribute("user");
			request.setAttribute("admin", admin);
			forwardList (request, response, user);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("MenuServlet.doPost");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{
			User user = (User) session.getAttribute("user");
			if (user instanceof Kunde) response.sendRedirect("changeAccount");
			AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
			if (request.getParameter("button") != null)
			{
				String button = request.getParameter("button");
		
				switch (button) 
				{
				case "change":
					changePassword(request, response);
					break;
				}
			}
		}
	}
	
	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		String error = "";
		String endSession = "  ";
		System.out.println(request.getParameter("email"));
		String email = request.getParameter("email");
		String pw_old = request.getParameter("pw_old");
		String pw_new = request.getParameter("pw_new");
		String pw_new2 = request.getParameter("pw_new2");
		if (!email.isEmpty())
		{
			System.out.println("A");
			error = error + userFunctions.accountAendern(email, pw_old, pw_new, pw_new2);
		}
		else
		{
			System.out.println("B");
			error = error + "Eingabe fehlt!";
		}
		if (error.equals(endSession))
		{
			error="";
		}
		if (!error.isEmpty())
		{
			request.setAttribute("error", error);
			request.getRequestDispatcher("AdminContent.jsp").include(request, response);
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
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		request.getRequestDispatcher("AdminAccountEdit.jsp").include(request, response);
		session.removeAttribute("error");
	}
}
