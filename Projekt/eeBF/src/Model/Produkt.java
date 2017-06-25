package Model;

public class Produkt {
	private int id;
	private String name;
	private String description;
	private float price;
	private int quantity;
	private String category;
	private Lager storage;
	
	public Produkt(int id, String name, String description, float price, int quantity, String category, Lager storage) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category= category;
		this.storage = storage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
