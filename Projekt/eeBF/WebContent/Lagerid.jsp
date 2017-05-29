<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Content Seite Lagerstand</title>
		
		<!-- Bootstrap import -->
		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<!-- SEHR UNSCHÖN GELÖST FÜR DEN ABSTAND DER BUTTONS -->
		<style>
		 small { visibility: hidden; }
		</style>
		
	</head>   
	<body style="margin:20px auto">  
		<div class="container">
		<div class="row header" style="text-align:center"></div>


			<div class="panel panel-default">
			  <div class="panel-heading">Produkte suchen</div>
			  <div class="panel-body">
			  

			  		<form action="admin" method="post">
						<input type="text" class="form-control" name="sid" placeholder="Lager ID">
						<br>
						<button type="submit" class="btn btn-primary" name="button" value="lagerstand" >Suchen</button>
					</form>
			  </div>
			</div>
			
			
		<!-- Produkte Tabelle -->
		<form action="admin" method="post" id="TableForm" role="form" >   
			<c:choose>
                    <c:when test="${not empty produktList}">
                        <table id="myTable" class="table table-striped">
                         
                            <tr><th> ID </th>
                            <th> Bezeichnung </th>
                            <th> Beschreibung </th>
                            <th> Preis </th>
                            <th> Menge </th></tr>
                            
                            <c:forEach var="produkt" items="${produktList}">
                                <tr>
                                <td>${produkt.id}</td>
                                <td>${produkt.name}</td>
                                <td>${produkt.description}</td>
                                <td>${produkt.price}</td>
                                <td>${produkt.quantity}</td>
                                </tr>
                            </c:forEach> 
                                          
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                  			Keine Produkte gefunden
                        </div>
                    </c:otherwise>
                </c:choose>                                
            </form>
            
            
            
			<form action="admin" method="post">
						<input type="text" class="form-control" name="pid" placeholder="Produkt ID">
						<input type="text" class="form-control" name="menge" placeholder="neue Menge">
						<br>
						<button type="submit" class="btn btn-primary" name="button" value="menge" >Change</button>
					</form>
			
			
		</div>
	</body> 

	 <!-- Javascript: Table Functions -->
	<script>
	$(document).ready(function()
	{
		$('#myTable').dataTable();
	});
	</script>
</html>  