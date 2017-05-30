<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Einkaufswagen</title>
		
		<%@ include file="Includes/Head.jsp"%>
	
	</head>

	<body>
		<div class="container">
			<!-- header -->
			<%@ include file="Includes/HeaderKunde.jsp"%>
			
			
			<!-- TODO just for testing -->
<!-- 			<form action="ShoppingCart" method="post"> -->
<!-- 				<input type="text" name="itemquantity" size="2" value="1"/> -->
<!-- 				<button name="additem" type="submit" value="3">Add Item</button> -->
<!-- 			</form> -->
			
<!-- 			<form action="ShoppingCart" method="post"> -->
<!-- 				<input type="text" name="itemquantity" size="2" value="2"/> -->
<!-- 				<button name="additem" type="submit" value="4">Add Item</button> -->
<!-- 			</form> -->
	
			<!-- items in shopping cart -->
	<%-- 		<c:out value="${shoppingCart.getId()}" /> --%>
			
			<c:choose>
				<c:when test="${not empty shoppingCart.getItems()}">
					<table class="shoppingcart">
						<thead>
							<tr>
								<th>Produkt ID</th>
								<th>Produktname</th>
								<th>Stück</th>
								<th>Preis pro Stück</th>
								<th class="left">Gesamtpreis</th>
							</tr>
						</thead>
						<c:forEach var="item" items="${shoppingCart.getItems()}">
							<tr>
								<td><c:out value="${item.getProduct().getId()}"/></td>
								<td><c:out value="${item.getProduct().getName()}"/></td>
								<td><c:out value="${item.getQuantity()}"/></td>
								<td><c:out value="${item.getPricePerUnit()}"/> €</td>
								<td class="left"><c:out value="${item.getQuantity() * item.getPricePerUnit()}" /> €</td>
							</tr>
						</c:forEach>
						<tr>
							<td></td><td></td><td></td><td></td>
							<td id="totalprice" class="left"><c:out value="${shoppingCart.getTotalPrice()}"/> €</td>
						</tr>
					</table>
					<div class="checkout">
						<form action="ShoppingCart" method="get">
							<button name="checkout" class="btn btn-success" type="submit">Zur Kasse gehen</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div>Ihr Einkaufswagen ist noch leer.</div>
				</c:otherwise>	
			</c:choose>
		</div>
	</body>
	
<!-- Javascript: Table Functions -->
<script>
	$(document).ready(function() {
		$('#shoppingcart').dataTable();
	});
</script>
</html>