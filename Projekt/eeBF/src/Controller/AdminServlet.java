package Controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;


@WebServlet (name="AdminServlet",
			urlPatterns={"/admin"})

public class AdminServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{ 
			// include content if attribute login is set
			User user = (User) session.getAttribute("user");
			if (user instanceof Kunde) response.sendRedirect("index");
			if (user instanceof Admin) 
			{
				AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
				if (request.getParameter("view") != null) 
				{
					System.out.println(request.getParameter("view"));
					if (request.getParameter("view").equals("userShow")) 
					{	
						ArrayList<User> userList = userFunctions.showUserList();
						request.setAttribute("userList", userList);
						System.out.println(userList);
						//ArrayList<Integer> userType = new ArrayList<Integer>();
						String add = "AdminUserShow.jsp";
						forwardList (request, response, add);
					} 
					else if (request.getParameter("view").equals("userCreate")) 
					{
						String add = "AdminUserCreate.jsp";
						forwardList (request, response, add);
					}
					else if (request.getParameter("view").equals("kategorie")) 
					{
						ArrayList<Produktkategorie> kategorieList = userFunctions.showKategorieList();
						request.setAttribute("kategorieList", kategorieList);
						String add = "AdminProductgroupCreate.jsp";
						forwardList (request, response, add);
					}
				} 
				else 
				{
					String add ="";
					forwardList(request, response, add);
				}
			}
		}			
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		System.out.println("adminServlet.doPost");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")==null)
		{  
			response.sendRedirect("login");
		}
		else if (session.getAttribute("user")!=null)
		{
			User user = (User) session.getAttribute("user");
			if (user instanceof Kunde) response.sendRedirect("index");
			if (user instanceof Admin) {
				AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
				if (request.getParameter("button") != null) {
					String button = request.getParameter("button");
					switch (button) {
					case "erstellen":
						createProduktgruppe(request,response);
						break;
					case "changePassword":
						response.sendRedirect("changePassword");
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
	
	public void createProduktgruppe (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		String name =request.getParameter("productgroup");
		userFunctions.erstelleProduktgruppe(name);
		String add = "";
		response.sendRedirect("admin?view=kategorie");
	}
	
	public void forwardList(HttpServletRequest request, HttpServletResponse response, String add) throws ServletException, IOException {
		System.out.println("admin.forwardList");
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("AdminContent.jsp").include(request, response);
		if(!add.isEmpty())
		{
			request.getRequestDispatcher(add).include(request, response);
		}
	}
}
