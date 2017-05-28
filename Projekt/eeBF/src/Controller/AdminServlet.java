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
						System.out.println("doget product group.jsp aufruf");
						String add = "AdminProductgroupCreate.jsp";
						forwardList (request, response, add);
					}
					else if (request.getParameter("view").equals("product")) 
					{
						//request.getRequestDispatcher("addProdukt").include(request, response);
						String add = "ProduktErstellen.jsp";
						forwardList (request, response, add);
					}
					else if (request.getParameter("view").equals("lagerstand")){
						System.out.println("doget Lagerid.jsp aufruf");
						String add = "Lagerid.jsp";
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
					case "product":{
						System.out.println("OKK");
						createProdukt(request, response);
						break;}
					case "erstellen":
					{
						System.out.println("switch case");
						createProduktgruppe(request,response);
						break;}
					case "changePassword":{
						response.sendRedirect("changePassword");
						break;}
					case "logout":{
						request.getRequestDispatcher("login").include(request, response);
						response.setContentType("text/html");
						break;}
					case "lagerstand": {
						System.out.println("switch case lagerstand");
						int id = Integer.parseInt(request.getParameter("sid"));
						//session.setAttribute("lagerid", id);
						request.setAttribute("lagerid", id);
						lagerStand(request, response);
						break;
					}
					
					
					
					}
				}
			}
		}
		
		//doGet(request, response);
	}
	
	public void createProdukt (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		System.out.println("aufgerufen produkt");
		HttpSession session = request.getSession(true);
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		Integer pid =Integer.parseInt(request.getParameter("id"));
		String bezeichnung =request.getParameter("productbezeichnung");
		String beschreibung = request.getParameter("productbeschreibung");
		String preis = request.getParameter("preis");
		String menge = request.getParameter("menge");
		String sid = request.getParameter("sid");
		userFunctions.erstelleProdukt(pid, bezeichnung, beschreibung, Integer.parseInt(preis), Integer.parseInt(menge), Integer.parseInt(sid));
		String add = "";
		response.sendRedirect("admin?view=kategorie");
	}
	
	public void createProduktgruppe (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("aufgerufen gruppe");
		HttpSession session = request.getSession(true);
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		String name =request.getParameter("productgroup");
		userFunctions.erstelleProduktgruppe(name);
		String add = "";
		response.sendRedirect("admin?view=kategorie");
	}
	
	public void lagerStand (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("aufgerufen lagerstand");
		HttpSession session = request.getSession(true);
		AdminFunctions userFunctions = (AdminFunctions) session.getAttribute("userFunctions");
		userFunctions.showProductsByLagerId((int)request.getAttribute("lagerid"));

		response.sendRedirect("admin?view=lagerstand");
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
