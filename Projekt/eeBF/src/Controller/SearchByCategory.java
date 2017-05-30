package Controller;

import java.io.*;
import java.net.URLEncoder;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
	import javax.servlet.http.HttpServletRequest;  
	import javax.servlet.http.HttpServletResponse;

  
@WebServlet(name="SearchByCategory",urlPatterns={"/SearchByCategory"})
	public class SearchByCategory extends HttpServlet {  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {    
     
      
    String name=request.getParameter("category");
   
  
     response.setContentType( "text/html" );
    response.sendRedirect( "ListOfSearchByCategory.jsp?foo="+name );   }  
    
}  
 