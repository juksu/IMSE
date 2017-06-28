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

public class CreateAdminServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("AdminUserCreate.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String pwcheck = request.getParameter("pwcheck");
		if (!(email.isEmpty()) && !(password.isEmpty()) && !(pwcheck.isEmpty()))
		{
			if (password.equals(pwcheck))
			{
				System.out.println("pwcheck passed");
				//IUserDAO userDAO = new MysqlUserDAO();
				IUserDAO userDAO = new MongodbUserDAO();
				Admin user = new Admin(0,email, password, null);
				System.out.println(user);
				userDAO.saveAdmin(user);
				response.sendRedirect("login");
			}
			else
			{
				System.out.println("pwcheck failed");
				request.setAttribute("error", "Wiederholtes Passwort nicht ident!");
				request.getRequestDispatcher("AdminUserCreate.jsp").include(request, response);
			}
		} 
		else
		{
			System.out.println("Felder dürfen nicht leer sein");
			request.setAttribute("error", "Eingabe fehlt!");
			request.getRequestDispatcher("AdminUserCreate.jsp").include(request, response);
		}
	}
}
