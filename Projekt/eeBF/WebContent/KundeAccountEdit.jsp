<!-- v1.0 -->
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
			  <div class="panel-heading">Daten ändern</div>
			  <div class="panel-body">
			  		<form action="accountEdit" method="post">
			  			<input type="text" class="form-control" name="email" value="${kunde.email }" placeholder="E-Mail">
			  			<br>
			  			<input type="password" class="form-control" name="pw_old" placeholder="Altes Passwort">
			  			<br>
						<input type="password" class="form-control" name="pw_new" placeholder="Neues Passwort">
						<br>
						<input type="password" class="form-control" name="pw_new2" placeholder="Neues Passwort wiederholen">
						<br>
						<br>
						<input type="text" class="form-control" name="nachname" value="${kunde.nachname}" placeholder="Nachname">
						<br>
						<input type="text" class="form-control" name="vorname" value="${kunde.vorname}" placeholder="Vorname">
						<br>
						<input type="text" class="form-control" name="land" value="${kunde.land}" placeholder="Land">
						<br>
						<input type="text" class="form-control" name="plz" value="${kunde.plz}" placeholder="PLZ">
						<br>
						<input type="text" class="form-control" name="ort" value="${kunde.ort}" placeholder="Stadt">
						<br>
						<input type="text" class="form-control" name="strasse" value="${kunde.strasse}" placeholder="Strasse">
						<br>
						<input type="text" class="form-control" name="hausnummer" value="${kunde.hausnummer}" placeholder="Hausnummer">
						<br>
						<button type="submit" class="btn btn-primary" name="button" value="change" id="changeinfoslog">Los!</button>
						<br>
						<br>
						<button type="submit" class="btn btn-danger" name="button" value="lock" id="lockid">Account permanent sperren!</button>
						
						<br>
						<div>
					      <p class="text-danger"><c:out value="${error}" escapeXml="false" /></p>
					    </div>
					</form>
			  </div>
			</div>
		</div>
	</body> 
</html>  