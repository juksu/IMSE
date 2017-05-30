<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "DAO.*"%>
<%@ page import= "java.util.*" %>
<%@page import= "java.util.Date" %>
<%@page import= "Model.*" %>
<%@page import= "Controller.*" %>

<html>
<head>
<style type="text/css">body {background-color: #99FF66;background-image: url(http://www.topglobus.ru/fotoalbum/pozadi/galerie/520.jpg);}</style>
 <style> 
.zebra {
  list-style: none;
  border-left: 10px solid #FC7574;
  padding: 0;
  font-family: "Lucida Sans";
}
.zebra li {
  padding: 10px;
}
.zebra li:nth-child(odd) {
  background: #E1F1FF;
}
.zebra li:nth-child(even) {
  background: white;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>The list of products</title>
</head>
<body>
 <a href="UserContent.jsp">Back to menu</a>


<ul class="zebra">
<%@ page import= "DAO.*"%>
<%@ page import= "java.util.*" %>
<%
String lis = request.getParameter("foo");
MysqlProduktDAO object = new MysqlProduktDAO();
ArrayList<Produkt> list =new ArrayList<Produkt>();
list=object.searchByCategory(lis);


for (int i=0;i<list.size();i++)
          {

              out.println("<li>"+ "<p>"+"Product ID: "+list.get(i).getId()+"<br>"+
            		  "Name of Product: "+list.get(i).getName()+"<br>"+
            		  "Description: "+list.get(i).getDescription()+"<br>"+
            		  "Price: "+ list.get(i).getPrice()+"<br>" +
            		  "Quantity:" +  list.get(i).getQuantity()+"<br>"+
            		  "Storage: " + list.get(i).getStorage()+"<br>"+"</li>"+"</p>" +
            		  
						"<form action='ShoppingCart' method='post'>" +
						"<input type='text' name='itemquantity' size='2' value='1'/>" +
						"<button name='additem' type='submit' value='" + 
						list.get(i).getId() + 
						"'>Produkt in Einkaufswagen legen</button>" + 
						"</form>");}
						

%>
</ul>
</body>
</html>