<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Einkaufswagen</title>
		
		
		<!-- Bootstrap import -->
<!-- 		<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">    -->
<!-- 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
<!-- 		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></link> -->
<!-- 		<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script> -->
<!-- 		<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
		
	</head>

	<body>
		<!-- header -->

		<!-- items in shopping cart -->
		<ul>
			<c:forEach var="item" items="shoppingcart">
				<li>
					<c:out value="${item.name}" />
					<c:out value="${item.quantity}" />
				</li>
			</c:forEach>
		</ul>

	</body>
</html>