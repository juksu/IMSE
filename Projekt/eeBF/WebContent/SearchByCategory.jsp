<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<style type="text/css">body {background-color: #99FF66;background-image: url(http://www.topglobus.ru/fotoalbum/pozadi/galerie/520.jpg);}</style>
<style>
form {
    border: 3px solid #000080;
}

  select {
       border: 0 none;
    color: white;
    background: black;
    font-size: 20px;
    font-weight: bold;
    padding: 2px 10px;
    width: 378px;
    
}
#mainselection {
    overflow: hidden;
    width: 350px;
    -moz-border-radius: 9px 9px 9px 9px;
    -webkit-border-radius: 9px 9px 9px 9px;
    border-radius: 9px 9px 9px 9px;
    box-shadow: 1px 1px 11px #330033;
    background: #87CEFA;
}
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
   margin: 8px 0;
    border: none;
    cursor: pointer;
    width: auto;
}

h1 {
    color: black;  text-align: center;
   
}
.button {
    background-color: #87CEFA; /* Green */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}
.button1 {
    background-color: white; 
    color: black; 
    border: 2px solid #000080;
}

.button1:hover {
    background-color: #87CEFA;
    color: white;
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search Product by Category</title>
<%@ page import= "DAO.*" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>
</head>

<body>

<br>
<br>
<br>
<br>
<br>

<table>

<tr>
<td><h1><font face="Comic sans MS" size="5">Product Category</font></h1></td><br />

<br>
<br>
</table>

<form method="post" action="SearchByCategory" >
<p align="center"> 
  <select id="mainselection" name="category" > 
  <option value="-1">-Select Category-</option>
 
  <%
MysqlProduktkategorieDAO category=new MysqlProduktkategorieDAO();
ArrayList <Produktkategorie> categoryList =new ArrayList<Produktkategorie> ();
categoryList= category.getAllProduktkategorie();
for(Produktkategorie produktSuchen: categoryList){		
%>
   <option value="<%out.print(produktSuchen.getTitel());%>"><%out.print(produktSuchen.getTitel());%></option><% }%>
</select>

    
</select>

<input type="submit"  class="button button1" value="Search Product" />





</form>
<br>
<br>
 <a href="UserContent.jsp">Back to menu</a>
</body>

</html>



