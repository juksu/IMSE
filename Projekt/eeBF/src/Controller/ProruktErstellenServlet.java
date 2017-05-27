package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.IProduktDAO;
import Model.AdminFunctions;

/**
 * Servlet implementation class ProruktErstellenServlet
 * 
 */



@WebServlet(description = "Add Produkt", urlPatterns = { "/addProdukt" })
public class ProruktErstellenServlet extends HttpServlet {
	
	private IProduktDAO produktDAO;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProruktErstellenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("/ProduktErstellen.jsp").include(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		String productbezeichnung = request.getParameter("productbezeichnung");
		String productbeschreibung = request.getParameter("productbeschreibung");
		
		produktDAO.createProdukt(id, productbezeichnung, productbeschreibung, 0, 0, null);
		
		
		
		doGet(request, response);
	}

}
