package Controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.IBestellpositionDAO;
import DAO.IBestellungDAO;
import DAO.IProduktDAO;
import DAO.MysqlBestellpositionDAO;
import DAO.MysqlBestellungDAO;
import DAO.MysqlProduktDAO;
import Model.Bestellposition;
import Model.Bestellung;
import Model.Kunde;
import Model.Produkt;
import Model.User;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = "Einkaufswagen.jsp";
		if( request.getParameter( "additem" ) != null )
		{
			redirect = addItemToShoppingCart( request, response );
		}
		else if( request.getParameter( "checkout"  ) != null )
//			response.sendRedirect( "Kasse.jsp" );
			redirect = "Kasse.jsp";
		else if( request.getParameter( "pay" ) != null )
			redirect = "Bestellzusammenfassung.jsp";	//TODO sollte natürlich zum Bezahlservice weiterleiten
		response.sendRedirect( redirect );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String addItemToShoppingCart( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{	
		HttpSession session = request.getSession(false);
		
		String redirect = "Einkaufswagen.jsp";
		
		if( session == null )
			redirect = "login";
		if( session.getAttribute( "user" ) == null )
			redirect = "login";
		
		// TODO test case
		// User user = new Kunde(2, "mymail", null, null, null, null, 0, null, null, 0, null);
		User user = (User) session.getAttribute( "user" );
		
		// only Kunden can order
		if( user instanceof Kunde )
		{
			Kunde customer = (Kunde) user;
			Bestellung shoppingCart = (Bestellung) session.getAttribute( "shoppingCart" );
			IBestellungDAO ioOrder = new MysqlBestellungDAO();
			IProduktDAO ioProduct = new MysqlProduktDAO();
			IBestellpositionDAO ioPos = new MysqlBestellpositionDAO();
			
			if( shoppingCart == null )
			{
				shoppingCart = new Bestellung();
				
				shoppingCart.setDate( Calendar.getInstance() );
				shoppingCart.setOrderStateOrdered( false );
				shoppingCart.setCustomer( customer );
				
				System.out.println( "shoppingCart session created" );
				
				System.out.println( "shoppingCart id = " + shoppingCart.getId() );
				ioOrder.insertBestellung( shoppingCart );
				System.out.println( "shoppingCart id = " + shoppingCart.getId() );
			}
			
			int pid = Integer.parseInt( request.getParameter( "additem" ) );
			int quantity = Integer.parseInt( request.getParameter( "itemquantity" ) );
			
			Produkt prod = ioProduct.getProduktById( pid );
			
			// TODO catch case if product could not be found in database
			Bestellposition newItem = new Bestellposition( prod, quantity, prod.getPrice() );
			ioPos.insertBestellposition( shoppingCart, newItem );
			
			shoppingCart.addItem( newItem );
			ioOrder.updateBestellung( shoppingCart, true );
			
			// request.setAttribute( "shoppingCart", shoppingCart );
			session.setAttribute( "shoppingCart", shoppingCart );
		}
		else 
		{
			redirect = "index";
		}
		
//		response.sendRedirect( redirect );
		return redirect;
		
	}
	
	

}
