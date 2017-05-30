package Controller;

import java.io.*;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
	import javax.servlet.http.HttpServletRequest;  
	import javax.servlet.http.HttpServletResponse;

  
@WebServlet(name="MengePruefen",urlPatterns={"/MengePruefen"})
	public class MengePruefen extends HttpServlet {  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {    
     
      
    String name=request.getParameter("keyword");
   
  
     response.setContentType( "text/html" );
    response.sendRedirect( "MengePruefenResult.jsp?foo="+name );   }  
    
}  
 