package Model;

public class Bestellpositionen {
	int id;
	Bestellung order;
	Produkt item;
	int quantity;
	
	// TODO Preis auch für Bestellposition bzw Bestellung gesamt?
	// Es kann sich der Preis des Stücks ändern aber wir wollen nach wie vor den Usprünglichen Handelspreis wissen.
	
	// TODO in logischem Entwurf besser die Bestellposition bei der Bestellung zu haben. 
	// Also Eine Liste von Positionen bei der Bestellung zu haben, sonst langwierige Suche durch alle Positionen für die Bestellung von nöten
	// Die Datenbank kann (muss?) das so machen aber nicht Java.
	
	public Bestellpositionen(int id, Bestellung order, Produkt item, int quantity) {
		this.id = id;
		this.order = order;
		this.item = item;
		this.quantity = quantity;
	}

}
