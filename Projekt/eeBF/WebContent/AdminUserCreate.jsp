<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Content Seite Benutzer erstellen</title>
		
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


			<div class="panel panel-default">
			  <div class="panel-heading">Benutzer erstellen</div>
			  <div class="panel-body">
			  
				<!-- Dropwdown User Type -->
				<div class="dropdown">
				  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">User Typ
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				  	<li><a href="#">Admin</a></li>
				  	<li><a href="#">Forscher</a></li>		  
				  </ul>
				 </div>
				  
				  <br>
				  <br>
				  
			  		<form action="login" method="post">
						<input type="text" class="form-control" name="emailnewuser" placeholder="E-Mail">
						<br>
						<input type="password" class="form-control" name="pwnewuser" placeholder="Passwort">
						<br>
						<br>
						<button type="submit" class="btn btn-primary" name="register" value="Register" >Registrieren</button>
					</form>
			  </div>
			</div>
		</div>
	</body> 
</html>  