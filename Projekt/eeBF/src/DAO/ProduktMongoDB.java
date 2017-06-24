package DAO;

import java.util.ArrayList;

import Model.Produkt;

public class ProduktMongoDB implements IProduktDAO {

	@Override
	public void createProdukt(int id, String name, String description, int price, int quantity, int lagerid) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAnzahl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBezeichnung(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produkt> getAllProdukten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produkt> getAllProduktenByLagerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newMenge(int pid, int newmenge) {
		// TODO Auto-generated method stub

	}

	@Override
	public Produkt getProduktById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
