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


public class LoginServlet extends HttpServlet {
	
	private MysqlUserDAO userDAO = new MysqlUserDAO();
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		

		
		//TODO teste nach userType um auf verschiedene seiten weiterzuleiten.
		if( session.getAttribute("user")==null){
			System.out.println("LoginServlet.doGet: if");
			request.getRequestDispatcher("Login.jsp").include(request, response);
			session.removeAttribute("error");
		}
		else{ // include content if attribute login is set
			System.out.println("LoginServlet.doGet: else");
			response.sendRedirect("index");
			session.removeAttribute("error");
			//request.getRequestDispatcher("index");//.include(request, response);
		}			
		response.setContentType("text/html");	// some browser need this (not all)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet.doPost");
		HttpSession session = request.getSession(true);
		if(request.getParameter("button")!=null){
			if (request.getParameter("button").equals("logout")) {
				System.out.println("Sessoin with userID: " + ((User) session.getAttribute("user")).getId() + " ausgeloggt.");
				session.invalidate();
				request.getRequestDispatcher("index.html").include(request, response);
				response.setContentType("text/html");
			}
		}
		else if(request.getAttribute("user") == null){	

			String type=request.getParameter("type");
			System.out.println(type);
			if (type !=null) {
				switch (type) {
				case "login": {
					userLogin(request, response);
					break;
				}
				case "register": {
					System.out.println("case:register");
					response.sendRedirect("register");
					break;}
				} 
			} else doGet(request,response);

	}
		
	 
	}
	public void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String email = (String)request.getParameter("email");
		String pw = (String)request.getParameter("pw");
		System.out.println(email);
		System.out.println(pw);
		
		try {
			User user=userDAO.loginUser(email, pw);
			UserFunctions userFunctions = null;
			if (user instanceof Admin) {
				userFunctions = new AdminFunctions((Admin)user);
			} else if (user instanceof Kunde) {
				userFunctions = new KundenFunctions((Kunde) user);
			} else {
				System.out.println("Loginservlet.doPost: UserFunktions konnten nicht erstellt werden");
			}
			if (user != null) {
				if (user.isInvalid())
			 	{
			 		request.setAttribute("error", "Account gesperrt!");
			 		System.out.println("Loginservlet.doPost: Account gesperrt!" +user.getId());
			 		//response.sendRedirect("index");
			 		response.sendRedirect("login");
				}
				else
				{
					session.setAttribute("user", user);
					session.setAttribute("userFunctions", userFunctions);
					System.out.println("LoginServlet.doPost: eingeloggt " +user.getId());
				}
			}
		} 
		catch (IllegalArgumentException e)
		{
			session.setAttribute("error", "E-Mail oder Passwort falsch");
		}
	doGet(request, response);
	}
}
