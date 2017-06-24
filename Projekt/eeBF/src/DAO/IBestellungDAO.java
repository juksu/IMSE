package DAO;

import Model.Bestellung;

public interface IBestellungDAO {

	/**
	 * inserts a new order into the database
	 * @param order
	 */
	public void insertBestellung( Bestellung order );
	
	/**
	 * updates an order in the database
	 * the order must already exist (use insertBestellung to create a new order)
	 * updates all parameters beside the id and the customer
	 * @param order
	 */
	public void updateBestellung( Bestellung order );
	
}
