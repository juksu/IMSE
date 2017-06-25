package Model;

public class Bestellposition
{
	int posId;
	Produkt product;
	int quantity;
	float pricePerUnit;
	
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
