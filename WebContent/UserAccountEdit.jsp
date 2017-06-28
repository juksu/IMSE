<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Content Seite Account Edit</title>
		
		<%@ include file="Includes/Head.jsp" %>
		
	</head>   
	<body style="margin:20px auto">  
		<div class="container">
			
			<%@ include file="Includes/HeaderKunde.jsp"%>

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