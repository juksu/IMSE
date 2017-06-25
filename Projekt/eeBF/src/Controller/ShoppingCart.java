package Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.IBestellpositionDAO;
import DAO.IBestellungDAO;
import DAO.IProduktDAO;
import DAO.GetDAO;
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
		{
			redirect = placeOrder( request, response );
		}
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
		
		if( Utils.userLogedInCheck( session ) )
		{
			User user = (User) session.getAttribute( "user" );
			
			// only Kunden can order
			if( user instanceof Kunde )
			{
				int pid = Integer.parseInt( request.getParameter( "additem" ) );
				int quantity = Integer.parseInt( request.getParameter( "itemquantity" ) );
				
				IProduktDAO ioProduct = GetDAO.getProduktDAO();
				Produkt prod = ioProduct.getProduktById( pid );
				
				if( quantity > prod.getQuantity() )
				{
					System.out.println( "quantity to high" );
					session.setAttribute( "quantityError", true );
				}
				else
				{
					session.setAttribute( "quantityError", false );
					Kunde customer = (Kunde) user;
					Bestellung shoppingCart = (Bestellung) session.getAttribute( "shoppingCart" );
					IBestellungDAO ioOrder = GetDAO.getBestellungDAO();
					
					IBestellpositionDAO ioPos = GetDAO.getBestellpositionDAO();
					
					if( shoppingCart == null )
					{
						shoppingCart = new Bestellung();
						
						shoppingCart.setDate( new Date() );
						shoppingCart.setOrderStateOrdered( false );
						shoppingCart.setCustomer( customer );
						
						System.out.println( "shoppingCart session created" );
						
						System.out.println( "shoppingCart id = " + shoppingCart.getId() );
						ioOrder.insertBestellung( shoppingCart );
						System.out.println( "shoppingCart id = " + shoppingCart.getId() );
					}
					
					
					// TODO catch case if product could not be found in database
					Bestellposition newItem = new Bestellposition( prod, quantity, prod.getPrice() );
					ioPos.insertBestellposition( shoppingCart, newItem );
					
					shoppingCart.addItem( newItem );
					
					ioOrder.updateBestellung( shoppingCart );
					
					// request.setAttribute( "shoppingCart", shoppingCart );
					session.setAttribute( "shoppingCart", shoppingCart );
				}
			}
			else 
				redirect = "index";
		}
		else
			redirect = "login";
			
		return redirect;
		
	}
	
	public String placeOrder(HttpServletRequest request, HttpServletResponse response )
	{
		HttpSession session = request.getSession(false);
		
		String redirect = "Bestellzusammenfassung.jsp";

		if( Utils.userLogedInCheck( session ) )
		{
			User user = (User) session.getAttribute( "user" );
			
			// only Kunden can order
			if( user instanceof Kunde )
			{
				Bestellung shoppingCart = (Bestellung) session.getAttribute( "shoppingCart" );		
				
				if( shoppingCart != null )
				{
					shoppingCart.setDate( new Date() );
					shoppingCart.setPaypalTNr( request.getParameter( "paymentdata" ) );
					shoppingCart.setOrderStateOrdered( true );
					shoppingCart.setOrderStatePaid( true  );
					
					IBestellungDAO ioOrder = GetDAO.getBestellungDAO();
					ioOrder.updateBestellung( shoppingCart );
					
					System.out.println( "order placed" );
					System.out.println( shoppingCart.getOrderState() );
					
					// update the warehousequantity
					for( Bestellposition item : shoppingCart.getItems() )
					{
						Produkt prod = item.getProduct();
						int newProdQuantity = prod.getQuantity() - item.getQuantity();
						prod.setQuantity( newProdQuantity );
						
						IProduktDAO prodDAO = GetDAO.getProduktDAO();
						prodDAO.newMenge( prod.getId(), newProdQuantity );
						
					}

					// clear the shoppingcart	
					session.setAttribute( "shoppingCart", null );
				}
			}
			else
				redirect = "index";
		}
		else
			redirect = "login";
		
		
		return redirect;
	}
	

}
