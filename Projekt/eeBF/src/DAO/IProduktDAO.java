package DAO;
import java.util.ArrayList;
import Model.*;

public interface IProduktDAO {
	public void createProdukt(int id, String name, String description, float price, int quantity, Lager storage);
	public int getAnzahl();
	//public String getName(int id);
	public String getBezeichnung(int id);
	public ArrayList<Produkt> getAllProdukten();
	
}
