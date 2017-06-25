package Model;

public class Produkt {
	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Lager getStorage() {
		return storage;
	}

	public void setStorage(Lager storage) {
		this.storage = storage;
	}


}
