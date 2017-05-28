<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- Produkten Tabelle -->
		<form action="admin" method="post" id="TableForm" role="form" >   
			<c:choose>
                    <c:when test="${not empty produktList}">
                        <table id="myTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Produkt</th>
                                                                                                
                                </tr>
                            </thead>
                            <c:forEach var="produkt" items="${produktList}">
                                <tr>
                                <td>${produkt.id}</td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                  			Keine Produkten gefunden
                        </div>
                    </c:otherwise>
                </c:choose>                                
            </form>
            
             <!-- Javascript: Table Functions -->
	<script>
	$(document).ready(function()
	{
		$('#myTable').dataTable();
	});
	</script>
</body>
</html>