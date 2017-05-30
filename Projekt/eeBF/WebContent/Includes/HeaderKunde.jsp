<!-- <div class="container"> -->
<div class="row header" style="text-align: center">
	<!-- Logo -->
	<img style="display: block; text-align: center;" src="logo.png"
		width="" height="" border="0" alt="Bildtext"> <br>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<ul class="nav navbar-nav">
				<li>
					<!-- Buttons -->
					<form id="uploadForm" action="index" method="get">
						<button type="submit" class="btn btn-primary btn-md navbar-btn"
							name="showAuktion" value="ViewAll"
							style="BACKGROUND-COLOR: 6666ff;">Produkt Suchen</button>
						<button type="submit" class="btn btn-primary btn-md navbar-btn "
							name="showAuktion" value="ViewAbos"
							style="BACKGROUND-COLOR: 6666ff;">Verfügbarkeit prüfen</button>
						<button type="submit" class="btn btn-primary btn-md navbar-btn"
							name="showAuktion" value="ViewMine"
							style="BACKGROUND-COLOR: 6666ff;">Produkt bestellen</button>
					</form>
				</li>



				<li>
					<!-- Buttons -->
					<form id="uploadForm1" action="index" method="post">

						<!-- SEHR UNSCHÖN GELÖST FÜR DEN ABSTAND DER BUTTONS -->
						<small>_____</small>

						<button type="submit" class="btn btn-info btn-md navbar-btn "
							name="button" value="create" style="BACKGROUND-COLOR: 330066;">Bestellung
							abschließen</button>

						<!-- SEHR UNSCHÖN GELÖST FÜR DEN ABSTAND DER BUTTONS -->
						<small>___________________________</small>

						<button type="submit" class="btn btn-warning btn-sm navbar-btn"
							name="button" value="changeAccount">Account bearbeiten</button>
						<button type="submit" class="btn btn-danger btn-sm navbar-btn"
							name="button" value="logout">Logout</button>
					</form>

				</li>

			</ul>
		</div>
	</nav>
</div>
<!-- </div> -->
