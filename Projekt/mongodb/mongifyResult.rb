table "benutzerkonto" do
	column "aid", :integer
	column "Passwort", :string
	column "email", :string
	column "createdate", :datetime
	column "usertype", :string
end

table "bestellposition" do
	column "posid", :integer
	column "oid", :integer
	column "pid", :integer
	column "menge", :integer
	column "preisprostueck", :decimal
end

table "bestellung" do
	column "oid", :integer
	column "datum", :datetime
	column "bestellstatus", :string
	column "paypalTNr", :string
	#~ column "aid", :integer	// mongify export replaced by:
	column "benutzerkonto", :DBREf( "benutzerkonto", "id" )
end

table "kunde" do
	column "cid", :integer
	column "Nachname", :string
	column "Vorname", :string
	column "Land", :string
	column "PLZ", :integer
	column "Ort", :string
	column "Strasse", :string
	column "HausNr", :integer
	column "aid", :integer
end

table "lager" do
	column "sid", :integer
	column "Regalfach", :integer
	column "Regalplatz", :integer
end

table "lieferung" do
	column "lid", :integer
	column "lDatum", :datetime
	column "kosten", :decimal
	column "UPSLNr", :string
	column "oid", :integer
end

table "produkt" do
	column "pid", :integer
	column "PBezeichnung", :string
	column "PBeschreibung", :string
	column "Menge", :integer
	column "sid", :integer
end

table "produktkategorie" do
	column "kid", :integer
	column "bezeichnung", :string
	column "beschreibung", :string
	column "oberkategorie", :integer
end

table "zuordnung" do
	column "pid", :integer
	column "kid", :integer
end

