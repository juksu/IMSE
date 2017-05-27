package DAO;

import Model.Bestellung;

public interface IBestellungDAO {

	public void insertBestellung( Bestellung order );
	
	public void updateBestellung( Bestellung order, boolean updateTimestamp );
	
}
