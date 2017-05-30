<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Kasse</title>
		<%@ include file="Includes/Head.jsp"%>
	</head>
	<body>
		<div class="container">
			<%@ include file="Includes/HeaderKunde.jsp"%>
			
			
			<c:choose>
<%-- 				<c:when test="${ not empty user}"> --%>
				<c:when test="${ not empty user }">
				<c:choose>
					<c:when test="${ not empty shoppingCart }">
						<div class="container">
							<h1>Lieferadresse:</h1> 
							<dl>
								<dt>Name</dt>
								<dd> <c:out value="${user.getVorname()}"/> <c:out value="${user.getNachname()}"/> </dd>
								<dt>Anschrift</dt>
								<dd> 
									<c:out value="${user.getStrasse()}"/> <c:out value="${user.getHausnummer()}"/>,
									<c:out value="${user.getPlz()}"/> <c:out value="${user.getOrt()}"/>
								</dd>	
								<dt>
								</dt>
							</dl>
							<h1>Zahlung</h1>
							
<!-- 							<dl> -->
<!-- 								<dt>Zu Zahlender Betrag</dt> -->
<%-- 								<dd> <c:out value="${shoppingCart.getTotalPrice()}"/> €</dd> --%>
<!-- 								<dt>Zahlungsdaten:</dt> -->
<!-- 								<dd> -->
									<form action="ShoppingCart" method="get"> 
										<div><label>Bezahlservice: 
											<select name="paymentservice">
												<option>PayPal</option>
											</select>
										</label></div>
										
										<div>
											<label>Zahlungsdaten: <input type="text" name="paymentdata" size="20"/> </label>
										</div>
										<div><label>Zu Zahlender Betrag: </label> 
											<c:out value="${shoppingCart.getTotalPrice()}"/> € 
										</div>
										<div>
											<button name="pay" class="btn btn-success" type="submit">Bezahlen und Bestellung abschließen</button>
										</div>
									</form>
<!-- 								</dd> -->
<!-- 							</dl> -->
						</div>
					</c:when>
					<c:otherwise>
						<div>Sie müssen erst Produkte in ihren Einkaufswagen legen um die Bestellung abzuschließen.</div>
					</c:otherwise>
				</c:choose>
				</c:when>
				<c:otherwise>
					<c:redirect url="Login.jsp"/>
				</c:otherwise>
			</c:choose>
		
		</div>
	</body>
</html>