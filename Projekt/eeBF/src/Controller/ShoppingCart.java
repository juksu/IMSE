package Controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
//	private static final long serialVersionUID = 1L; 
	
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
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
		if( session.getAttribute( "user" ) == null )
			response.sendRedirect("login");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void addItemToShoppingCart( HttpServletRequest request, Produkt product, int quantity )
	{
		HttpSession session = request.getSession(false);
		User user = ((User) session.getAttribute( "user" ));
		
		// only Kunden can order
		if( user instanceof Kunde )
		{
			Kunde customer = (Kunde) user;
			Bestellung shoppingCart = (Bestellung) session.getAttribute("shoppingCart");
			
			if( shoppingCart == null )
			{
				shoppingCart = new Bestellung();
				
				shoppingCart.setDate( Calendar.getInstance() );
				shoppingCart.getCurrentState().setOrdered( false );
				shoppingCart.setCustomer( customer );
				
				session.setAttribute("shoppingCart", shoppingCart);
				
				// write to database and get the new id from there
				
			}
			
			Bestellposition item = new Bestellposition( product, quantity );
			
			// TODO write item to DB to get the id
					
			shoppingCart.addItem( item );
		}
		else 
		{
			// TODO
		}
		
	}
	
	

}
