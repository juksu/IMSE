<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Content Seite Account Edit</title>
		
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

			<!-- Logo -->
			<img style="display: block; text-align: center;" src="logo.png" width="" height="" border="0" alt="Bildtext">
			<br>


			<div class="panel panel-default">
			  <div class="panel-heading">Neues Passwort</div>
			  <div class="panel-body">
			  		<form action="login" method="post">
						<input type="password" class="form-control" name="newuserpw" placeholder="Passwort">
						<br>
						<input type="password" class="form-control" name="newuserpwagain" placeholder="Passwort wiederholen">
						<br>
						<input type="password" class="form-control" name="currentuserpw" placeholder="Aktuelles Passwort">
						<br>
						<button type="submit" class="btn btn-primary" name="changefroscherpassword" value="ChangeForscherPassword" id="changepasswordforscherlog">Passwort ändern</button>
						<br>
						<br>
						<button type="submit" class="btn btn-danger" name="changefroscherpassword" value="ChangeForscherPassword" id="changepasswordforscherlog">Account permanent sperren!</button>
					</form>
			  </div>
			</div>
		</div>
	</body> 
</html>  