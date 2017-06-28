<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Auktion erstellen Seite</title>
		
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
	
		<!-- Inputs -->
		<form action="createAuktion" method="post">
		<div class="form-group">
			<label for="tags">Titel</label>
			<input type="text" name="titel" class="form-control" id="tags">
		</div>
				
		<div class="form-group">
			<label for="startg">Startgebot</label>
			<input type="number" name="startgebot" min="1" class="form-control" id="startg">
		</div>
		
					<!-- TextArea für Bilder -->

					
					<br>
		<div>
			<p class="text-danger"><c:out value="${error}" escapeXml="false" /></p>
		</div>
		
		<br>
		<!-- TextArea -->
		<div class = "form-group">
		  <label for = "name">Info</label>
		  <textarea class = "form-control" name="text" rows = "5"></textarea>
		</div>
		
		<!-- Dropwdown Zeitraum -->
		<div class="dropdown">
		 <p>Dauer wählen</p> 
			<fieldset> 
				<input type="radio" id="1" name="dauer" value="1"> 
				<label for="au">1 Woche</label>
				<br>
			 	<input type="radio" id="2" name="dauer" value="2">
			  	<label for="el">2 Wochen</label>
			  	<br> 
			  	<input type="radio" id="3" name="dauer" value="3">
			    <label for="kl">3 Wochen</label>
			    <br> 
			  	<input type="radio" id="4" name="dauer" value="4">
			    <label for="kl">4 Wochen</label>
			</fieldset> 
			
		</div> 
		
				<!-- Kategorie Auswahl-->
	 <p>Kategorie wählen</p> 
		<fieldset>
			<div style="height: 6em; width: 12em; overflow: auto;">
				<c:forEach var="Produktkategorie" items="${kategorieList}">
					<c:if test="${Produktkategorie.titel != 'Abgelaufen'}">
						<input id="${Produktkategorie.titel}"type="radio" name="katAuswahl" value="${Produktkategorie.id}"> 
						<label for="${Produktkategorie.titel}">${Produktkategorie.titel}</label>
						<br>
					</c:if>

  				</c:forEach>
  				
		</div>
		</fieldset>
	
		
		<br>
		
		<br>
		<br>
		
		<!-- Buttons -->
		<button type="submit" class="btn btn-info" name="button" value="freigeben" >Auktion freigeben</button>
		<button type="submit" class="btn btn-warning" name="button" value="cancel" >Abbrechen</button>
		</form>
		</div>
	</body>
</html>