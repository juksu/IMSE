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
ProduktMongoDB object = new ProduktMongoDB();
int quantity=object.MengePruefen(lis);


              out.println("<li>"+ "<p>"+ "Quantity:" +  quantity +"<br>"+"</li>"+"</p>");
              

%>
</ul>
</body>
</html>