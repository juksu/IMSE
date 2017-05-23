package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

@WebServlet (name="tUserAccountEditServlet",
			urlPatterns={"/changePassword"})

public class ChangePasswordServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null){  
			System.out.println("MenuServlet.doGet: nicht eingeloggt, weiter auf login.jsp");
			response.sendRedirect("login");

		}
		else if (session.getAttribute("user")!=null){ // include content if attribute login is set
			User user = (User) session.getAttribute("user");
			//weiterleiten, falls user ein Kunde ist.
			if (user instanceof Kunde) response.sendRedirect("accountEdit");
			forwardList (request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("MenuServlet.doPost");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null){  
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null){
			User user = (User) session.getAttribute("user");
			//weiterleiten, falls user ein Kunde ist.
			if (user instanceof Kunde) response.sendRedirect("changeAccount");

			if (request.getParameter("button") != null) {
				String button = request.getParameter("button");
				
				switch (button) {
				
				case "change":
					changeData(request, response);
					break;
				}
			
			}

		}

		
	}
	
	public void changeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		UserFunctions userFunctions = (UserFunctions) session.getAttribute("userFunctions");
		String pw_old = request.getParameter("pw_old");
		String pw_new = request.getParameter("pw_new");
		String pw_new2 = request.getParameter("pw_new2");
		String error = userFunctions.changePassword(pw_old, pw_new, pw_new2);
		session.setAttribute("error", error);
		if (error.isEmpty()) session.invalidate();
		forwardList(request, response);
	}
	
	public void forwardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//KundenFunctions userFunctions = (KundenFunctions) session.getAttribute("userFunctions");
		request.getRequestDispatcher("FroscherChangePassword.jsp").include(request, response);
		session.removeAttribute("error");
	}
}
