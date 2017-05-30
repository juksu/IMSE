package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class KundenServlet
 */
@WebServlet("/KundenServlet")
public class KundenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KundenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "UserContent.jsp";
		
		System.out.println( "Kundenservlet" );
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user") == null)
		{  
			redirect = "login";
		}
		else
		{
			if( request.getParameter( "useraction" ) != null )
			{
				String target = request.getParameter( "useraction" ); 
				
				if( target.equals( "searchbykeyword" ) )
					redirect = "SortByKeyword.jsp";
				if( target.equals( "searchbycategory" ) )
					redirect = "SearchByCategory.jsp";
				if( target.equals( "availability" ) )
					redirect = "MengePruefen.jsp";
				if( target.equals( "shoppingcart" ) )
					redirect = "Einkaufswagen.jsp";
				if( target.equals( "editaccount" ) )
					redirect = "UserAccountEdit.jsp";
				if( target.equals( "logout" ) )
				{
					session.invalidate();
					redirect = "index";
				}
			}
		}
		
		response.sendRedirect( redirect );
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
