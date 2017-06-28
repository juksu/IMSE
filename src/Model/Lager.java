package Model;

public class Lager {
	private int id;
	private int shelf;
	private int place;
	
	public Lager(int id, int shelf, int place) {
		this.id = id;
		this.shelf = shelf;
		this.place = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

}
