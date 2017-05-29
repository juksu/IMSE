package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
	//	if( session.getAttribute( "user" ) == null )
	//		response.sendRedirect("login");
		
		Bestellung shoppingCart = (Bestellung) session.getAttribute( "shoppingCart" );
		if( shoppingCart == null )
		{
			shoppingCart = new Bestellung();
			
//			public Bestellung(int id, Calendar date, OrderState currentState, Kunde customer, ArrayList<Bestellposition> items,
//					String paypalTNr)
			session.setAttribute( "shoppingCart", shoppingCart );
		}
		
		// TODO only for debug
//		PrintWriter writer = response.getWriter();
//		response.setContentType("text/html;charset=UTF-8");
//		writer.println("b#ase+üla");
		
		if( request.getParameter( "additem" ) != null )
		{
			addItemToShoppingCart( request, response );
//			
//			System.out.println( request.getParameter( "additem" ) );
//			System.out.println( request.getParameter( "itemquantity" ) );
			
//			try
//			{
//				int pid = Integer.parseInt( request.getParameter( "additem" ) );
//				int quantity = Integer.parseInt( request.getParameter( "itemquantity" ) );
//				
//				Produkt prod = new Produkt( pid, "name", "description", (float) (Math.random()*10), 0, null);
//				
//				Bestellposition newpos = new Bestellposition(prod, quantity, (float) (Math.random()*10)); 
//				
//				shoppingCart.addItem( newpos );
//				
//				// TODO testing
//				shoppingCart.setId( Integer.parseInt( request.getParameter( "additem" ) ) );
				
//			} 
//			catch ( NumberFormatException e )
//			{
//				// TODO
//			}
		}
		
//		request.setAttribute( "shoppingCart", shoppingCart );
//		session.setAttribute( "shoppingCart", shoppingCart );
//		System.out.println( shoppingCart.getId() );
//		response.sendRedirect("Einkaufswagen.jsp");
				
				
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void addItemToShoppingCart( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{	
		HttpSession session = request.getSession(false);
		
		if( session == null )
			response.sendRedirect( "login" );
		if( session.getAttribute( "user" ) == null );
			response.sendRedirect( "login" );
		
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
				
				ioOrder.insertBestellung( shoppingCart );
			}
			
			int pid = Integer.parseInt( request.getParameter( "additem" ) );
			int quantity = Integer.parseInt( request.getParameter( "itemquantity" ) );
			
			// TODO sample data 
			Produkt prod = new Produkt( pid, "name", "description", (float) (Math.random()*10), 0, null);
//			Bestellposition newpos = new Bestellposition(prod, quantity, (float) (Math.random()*10));
			
			
//			Produkt prod = ioProduct.getProduktById( pid );
			Bestellposition newItem = new Bestellposition( prod, quantity, prod.getPrice() );
			
			shoppingCart.addItem( newItem );
			
			// TODO testing
//			shoppingCart.setId( Integer.parseInt( request.getParameter( "additem" ) ) );
			

//			IBestellpositionDAO io = new MysqlBestellpositionDAO();
			
			ioPos.insertBestellposition( shoppingCart, newItem );
			shoppingCart.addItem( newItem );
			ioOrder.updateBestellung( shoppingCart, true );
			
//			session.setAttribute( "shoppingCart", shoppingCart );
			
			System.out.println( "bla " + shoppingCart.getId() );
			request.setAttribute( "shoppingCart", shoppingCart );
//			session.setAttribute( "shoppingCart", shoppingCart );
			response.sendRedirect("Einkaufswagen.jsp");
		}
		else 
		{
			// TODO
		}
		
	}
	
	

}
