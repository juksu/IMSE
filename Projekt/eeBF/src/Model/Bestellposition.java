package Model;

public class Bestellposition {

	Produkt item;
	int quantity;
	
	// TODO Preis auch für Bestellposition bzw Bestellung gesamt?
	// Es kann sich der Preis des Stücks ändern aber wir wollen nach wie vor den Usprünglichen Handelspreis wissen.
	
	public Bestellposition(Produkt item, int quantity)
	{
		this.item = item;
		this.quantity = quantity;
	}
	
}
