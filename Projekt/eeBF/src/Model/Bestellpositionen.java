package Model;

public class Bestellpositionen {
	int id;
	Bestellung order;
	Produkt item;
	int quantity;
	
	// TODO Preis auch für Bestellposition bzw Bestellung gesamt?
	// Es kann sich der Preis des Stücks ändern aber wir wollen nach wie vor den Usprünglichen Handelspreis wissen.
	
	public Bestellpositionen(int id, Bestellung order, Produkt item, int quantity) {
		this.id = id;
		this.order = order;
		this.item = item;
		this.quantity = quantity;
	}

}
