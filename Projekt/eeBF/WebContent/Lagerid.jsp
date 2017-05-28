<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lagerstand korrigieren</title>
</head>
	<body style="margin:20px auto">  
		<div class="container">
		<div class="row header" style="text-align:center"></div>

			<!-- Logo -->
			<img style="display: block; text-align: center;" src="logo.png" width="" height="" border="0" alt="Bildtext">
			<br>

<form method ="post" action="admin">
			<div class="panel panel-default">
			  <div class="panel-heading">Lagerstand korriegieren</div>
			  
			 	 <div class="panel-body">
			  	 <div class="container">
			  		<label><b>Lager ID</b></label>
       				<input type="text" class="form-control" name="sid" placeholder="Lagerid">
					<button type="submit" class="btn btn-primary" name="button" value="lagerstand" >Suchen</button>
				</div>
			  </div>
			  </form>
			</div>
		</div>
	</body> 
</html>