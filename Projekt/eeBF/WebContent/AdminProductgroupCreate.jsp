<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<html>
	<head>   
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Content Seite Erstelle Produktgruppe</title>
		
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
			  <div class="panel-heading">Produktkategorie erstellen</div>
			  <div class="panel-body">
			  

			  		<form action="admin" method="post">
						<input type="text" class="form-control" name="productgroup" placeholder="Neuer Produktgruppen-Name">
						<br>
						<button type="submit" class="btn btn-primary" name="button" value="erstellen" >Erstellen</button>
					</form>
			  </div>
			</div>
			
			
		<!-- Produktgruppen Tabelle -->
		<form action="admin" method="post" id="TableForm" role="form" >   
			<c:choose>
                    <c:when test="${not empty kategorieList}">
                        <table id="myTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Produktgruppe</th>
                                                                                                
                                </tr>
                            </thead>
                            <c:forEach var="produktkategorie" items="${kategorieList}">
                                <tr>
                                <td>${produktkategorie.titel}</td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                  			Keine Produktgruppen gefunden
                        </div>
                    </c:otherwise>
                </c:choose>                                
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