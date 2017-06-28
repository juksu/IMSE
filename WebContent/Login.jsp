<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Seite</title>
		
		<!-- Bootstrap import -->
		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></link>
		<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</head> 

	

	<body style="margin:20px auto"> 
		<div class="container">
			
			<!-- Logo -->
			<img style="display: block; text-align: center;" src="logo.png" width="" height="" border="0" alt="Bildtext">
			<br>
			<br>
			
			<div class="panel panel-default">
			  <div class="panel-heading">Login-Panel</div>
			  <div class="panel-body">
			  
			  		<form action="login" method="post">
						<input type="text" class="form-control" name="email" placeholder="E-Mail">
						<br>
						<input type="password" class="form-control" name="pw" placeholder="Passwort">
						<br>
						<div>
							<p class="text-danger"><c:out value="${error}" escapeXml="false" /></p>
						</div>
						<button type="submit" class="btn btn-primary" name="type" value="login" id="log">Einloggen</button>
						<br>
						<br>
						<button type="submit" class="btn btn-warning" name="type" value="register" >Registrieren</button>
					</form>
			  </div>
			</div>
		</div>
	</body> 
</html>




