package DAO;

// v1.1.2

import java.util.ArrayList;
import Model.*;

public interface IProduktDAO {
	public void createProdukt(int id, String name, String description, int price, int quantity, int lagerid);
	public int getAnzahl();
	//public String getName(int id);
	public String getBezeichnung(int id);
	public ArrayList<Produkt> getAllProdukten();
	public ArrayList<Produkt> getAllProduktenByLagerId(int id);
	
	public void newMenge(int pid, int newmenge);
	public Produkt getProduktById( int id );
	
	
}
