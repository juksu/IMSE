<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Content MainSeite</title>
		
		<!-- Bootstrap import -->
		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<!-- SEHR UNSCHÖN GELÖST FÜR DEN ABSTAND DER BUTTONS in der NAVleiste-->
		<style>
		 small { visibility: hidden; }
		</style>
		
	</head>   
	<body style="margin:20px auto">  
		<div class="container">
		<div class="row header" style="text-align:center"></div>

				<!-- Logo -->
				<img style="display: block; text-align: center;" src="logo.png" width="" height="" border="0" alt="Bildtext">
				<br>

		<!-- NavigationsLeiste -->
		<nav class="navbar navbar-inverse navbar-static-top">
	    <div class="container">
	        <ul class="nav navbar-nav">
	            <li>
				 </li>
				 <li>
				 		<!-- Buttons -->
						<form id="uploadForm1" action="admin" method="get">
							<small>__</small>
							<button type="submit" class="btn btn-primary btn-md navbar-btn" name="view" value="userShow" style="BACKGROUND-COLOR: 330066;">Benutzer ansehen</button>
							<button type="submit" class="btn btn-primary btn-md navbar-btn " name="view" value="userCreate" style="BACKGROUND-COLOR: 330066;">Benutzer erstellen</button>
							
							<small>______</small>
							<button type="submit" class="btn btn-primary btn-sm navbar-btn" name="view" value="kategorie" style="BACKGROUND-COLOR: 330066;">Produkt erstellen</button>
							<button type="submit" class="btn btn-primary btn-sm navbar-btn" name="view" value="kategorie" style="BACKGROUND-COLOR: 330066;">Lagerstand korrigieren</button>
						</form>
						<form id="uploadform2" action="admin" method="post">
							<small>___________________________________________________________________________________________________________________________________________</small>
							<button type="submit" class="btn btn-warning btn-sm navbar-btn" name="button" value="changePassword" >Account bearbeiten</button>
							<button type="submit" class="btn btn-danger btn-sm navbar-btn" name="button" value="logout">Logout</button>
						</form>
			     </li>
	        </ul>
	    </div>
		</nav>


		


	<br>
	<br>
	<br>


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