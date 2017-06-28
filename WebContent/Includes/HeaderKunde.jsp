<div class="row header" style="text-align: center"></div>
<!-- Logo -->
<img style="display: block; text-align: center;" src="logo.png" width=""
	height="" border="0" alt="Bildtext">
<br>


<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div>
			<form class="nav-form" action="KundenServlet" method="get">
				<button type="submit" class="btn btn-primary btn-md navbar-btn"
						name="useraction" value="searchbykeyword">
					Nach Produkt suchen
				</button>
				<button type="submit" class="btn btn-primary btn-md navbar-btn"
						name="useraction" value="searchbycategory">
					Nach Kategorie suchen
				</button>
				<button type="submit" class="btn btn-primary btn-md navbar-btn"
						name="useraction" value="availability">
					Verfügbarkeit überprüfen
				</button>
			</form>
		</div>
		<div>
			<form class="nav-form" action="KundenServlet" method="get">
				<button type="submit" class="btn btn-primary btn-md navbar-btn"
						name="useraction" value="shoppingcart">
					Einkaufswagen
				</button>

				<button type="submit" class="btn btn-warning btn-md navbar-btn"
						name="useraction" value="editaccount">
					Account bearbeiten
				</button>
				<button type="submit" class="btn btn-danger btn-md navbar-btn"
						name="useraction" value="logout">
					Logout
				</button>
			</form>
		</div>
	</div>
</nav>
