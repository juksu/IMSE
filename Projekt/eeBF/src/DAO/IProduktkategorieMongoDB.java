package DAO;


import java.util.ArrayList;
import Model.*;

public interface IProduktkategorieMongoDB {
	
	public void createProduktkategorie(String name, String beschreibung);
	public int getAnzahl();
	public String getName(int id);
	public String getBeschreibung(int id);
	public ArrayList<Produktkategorie> getAllProduktkategorie();
	public int getIdByName(String name);
}
