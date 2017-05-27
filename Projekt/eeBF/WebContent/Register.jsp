<!-- v1.0 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Register Seite</title>
		
		<!-- Bootstrap import -->
		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
	</head>   
	<body style="margin:20px auto">  
		<div class="container">
		<div class="row header" style="text-align:center"></div>

			<!-- Logo -->
			<img style="display: block; text-align: center;" src="logo.png" width="" height="" border="0" alt="Bildtext">
			<br>


			<div class="panel panel-default">
			  <div class="panel-heading">Formular</div>
			  <div class="panel-body">
			  		<form action="register" method="post">
						<input type="text" class="form-control" name="email" placeholder="E-Mail">
							<br>
						<input type="password" class="form-control" name="password" placeholder="Passwort">
							<br>
						<input type="password" class="form-control" name="pwcheck" placeholder="Passwort">
							<br>
						<input type="text" class="form-control" name="vorname" placeholder="Vorname">
							<br>
						<input type="text" class="form-control" name="nachname" placeholder="Nachname">
							<br>
						<input type="text" class="form-control" name="land" placeholder="Land">
							<br>
						<input type="text" class="form-control" name="plz" placeholder="PLZ">
							<br>
						<input type="text" class="form-control" name="ort" placeholder="Ort">
							<br>
						<input type="text" class="form-control" name="strasse" placeholder="Strasse">
							<br>
						<input type="text" class="form-control" name="hausnummer" placeholder="Hausnummer">

						<br>
						<button type="submit" class="btn btn-primary" name="type" value="userRegister" id="userregisterlog">Account registrieren</button>
					</form>
			  </div>
			</div>
		</div>
	</body> 
</html>  