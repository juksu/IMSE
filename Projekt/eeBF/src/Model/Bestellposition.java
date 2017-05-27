package Model;

public class Bestellposition
{
	int posId;
	Produkt product;
	int quantity;
	float pricePerUnit;

	// TODO Preis auch für Bestellposition bzw Bestellung gesamt?
	// Es kann sich der Preis des Stücks ändern aber wir wollen nach wie vor den
	// Usprünglichen Handelspreis wissen.

	public Bestellposition(Produkt product, int quantity, float pricePerUnit)
	{
		this.product = product;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public Produkt getProduct()
	{
		return product;
	}

	// public void setItem(Produkt item) {
	// this.item = item;
	// }

	public int getQuantity()
	{
		return quantity;
	}

	// public void setQuantity(int quantity) {
	// this.quantity = quantity;
	// }

	public float getPricePerUnit()
	{
		return pricePerUnit;
	}

//	public void setPriceEach( float priceEach )
//	{
//		this.priceEach = priceEach;
//	}

}
