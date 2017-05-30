<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Content Seite Erstelle Produkt</title>
		
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
			  <div class="panel-heading">Produkt erstellen</div>
			  <div class="panel-body">
			  

			  		<form action="admin" method="post">
			  			<input type="text" class="form-control" name="id" placeholder="Neuer Produkt-ID">
						<input type="text" class="form-control" name="productbezeichnung" placeholder="Neuer Produkt-Bezeichnung">
						<input type="text" class="form-control" name="productbeschreibung" placeholder="Neuer Produkt-Beschreibung">
						<input type="text" class="form-control" name="preis" placeholder="Neuer Produkt-preis">
						<input type="text" class="form-control" name="menge" placeholder="Neue Produkt Menge">
						<input type="text" class="form-control" name="sid" placeholder="Lager ID">
						<br>
						<button type="submit" class="btn btn-primary" name="button" value="product" >Erstellen</button>
					</form>
			  </div>
			</div>
			
			
	
			
		</div>
	</body> 

	
</html>  