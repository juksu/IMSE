<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Content Seite</title>
	
	<%@ include file="Includes/Head.jsp"%>

</head>

<!-- <body style="margin: 20px auto"> -->
<body>
	<div class="container">

		<%@ include file="Includes/HeaderKunde.jsp"%>

		<!-- Dropwdown Kategorie -->

		<form name="KategorieSuche" method="get">
			<input type="hidden" name="${sessionScope.prev}"
				value="${sessionScope.prevDat}" /> <select name="Kategorie"
				id=KategorieSuche>
				<option value="All">Alle</option>
				<c:forEach var="Produktkategorie" items="${kategorieList}">
					<option value="${Produktkategorie.titel}">${Produktkategorie.titel}</option>
				</c:forEach>
			</select> <input type="submit" value="Filter" />
		</form>


		<!-- Unschöner Zeilenumbruch -->
		<br> <br> <br>

		<!-- Table -->
		<form action="index" method="post" id="TableForm" role="form">
			<c:choose>
				<c:when test="${not empty auktionenList}">
					<table id="myTable" class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Titel</th>
								<th>Beschreibung</th>
								<th>End Datum</th>
								<th>Aktueller Preis</th>
								<th>InterAktion</th>
							</tr>
						</thead>
						<c:forEach var="auktion" items="${auktionenList}">
							<tr>
								<td>${auktion.id}</td>
								<td>${auktion.titel}</td>
								<td>${auktion.beschreibung}</td>
								<td>${auktion.enddateSDF}</td>
								<td>${auktion.aktuellerPreis}&#8364</td>
								<td><c:if test="${sessionScope.prevDat == 'ViewAll'}">
										<button type="submit" class="btn btn-success"
											name="auktionDetail" value="${auktion.id}">Ansehen</button>
										<button type="submit" class="btn btn-success"
											name="abonnieren" value="${auktion.id}"
											<c:forEach var="aboauktion" items="${aboList}">
										<c:choose>

											    <c:when test="${aboauktion.id == auktion.id }"><c:out value="disabled='disabled'"/></c:when>
											    <c:otherwise>
											    </c:otherwise>
											
										</c:choose> 
										</c:forEach>>Abonnieren</button>

									</c:if> <c:if test="${sessionScope.prevDat == 'ViewMine'}">
										<button type="submit" class="btn btn-success"
											name="auktionDetail" value="${auktion.id}">Ansehen</button>
										<button type="submit" class="btn btn-success"
											name="schliessen" value="${auktion.id}"
											<c:if test="${auktion.isFinished == false}"> <c:out value="disabled='disabled'"/></c:if>>Schliessen</button>
									</c:if> <c:if test="${sessionScope.prevDat == 'ViewAbos'}">
										<c:set var="count" value="0" scope="page" />
										<button type="submit" class="btn btn-success"
											name="auktionDetail" value="${auktion.id}">Ansehen</button>
										<button type="submit" class="btn btn-success"
											name="deabonnieren" value="${auktion.id}"
											<c:forEach var="Integer" items="${gebotenList}">
		                      			<c:choose>
											<c:when test="${Integer == auktion.id}"><c:set var="count" value="${count + 1}" scope="page" /></c:when>
											<c:otherwise>
											</c:otherwise>
											
										</c:choose> 
										</c:forEach>
											<c:if test="${count > 0 }"><c:out value="disabled='disabled'"/></c:if>>Deabonnieren</button>

									</c:if></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<br>
					<div class="alert alert-info">Keine Produkte gefunden</div>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</body>

<!-- Javascript: Table Functions -->
<script>
	$(document).ready(function() {
		$('#myTable').dataTable();
	});
</script>
</html>
