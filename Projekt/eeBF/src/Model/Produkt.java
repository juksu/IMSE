package Model;

public class Produkt {
	private int id;
	private String name;
	private String description;
	private float price;
	private int quantity;
	private Lager storage;
	
	public Produkt(int id, String name, String description, float price, int quantity, Lager storage) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.storage = storage;
	}


}
